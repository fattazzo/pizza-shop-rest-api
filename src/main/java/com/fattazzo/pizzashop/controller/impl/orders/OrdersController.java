package com.fattazzo.pizzashop.controller.impl.orders;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.api.OrdersApi;
import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.api.OrderLine;
import com.fattazzo.pizzashop.model.api.OrderRequest;
import com.fattazzo.pizzashop.model.api.OrderSearchParameters;
import com.fattazzo.pizzashop.model.api.OrderSearchResult;
import com.fattazzo.pizzashop.model.api.OrderState;
import com.fattazzo.pizzashop.model.entity.OrderEntity;
import com.fattazzo.pizzashop.model.entity.OrderLineEntity;
import com.fattazzo.pizzashop.model.entity.UserEntity;
import com.fattazzo.pizzashop.model.entity.UserType;
import com.fattazzo.pizzashop.security.JwtUser;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.order.OrderSearchService;
import com.fattazzo.pizzashop.service.order.OrderService;
import com.fattazzo.pizzashop.service.user.UserService;
import com.fattazzo.pizzashop.websocket.controlles.orders.WSOrdersController;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "orders" })
public class OrdersController implements OrdersApi {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private LocaleUtilsMessage localeUtilsMessage;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderSearchService orderSearchService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderValidator orderValidator;

	@Autowired
	private WSOrdersController wsOrdersController;

	private final HttpServletRequest request;

	@Autowired
	public OrdersController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Transactional
	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'ORDERS','CREATE_ORDER'})")
	public ResponseEntity<Void> createOrder(@Valid OrderRequest body) {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		OrderDetails order = orderMapper.toOrderDetails(body, ((JwtUser) auth.getPrincipal()).getUsername());
		try {
			order = orderValidator.validate(order, request);
		} catch (final ValidatorException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("order.insert.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage(e.getMessageKey(), e.getMessageParams(), request))
					.status(e.getError()).build();
		}
		order.setState(OrderState.PENDING);

		OrderEntity orderEntity = mapper.map(order, OrderEntity.class);
		for (final OrderLine line : CollectionUtils.emptyIfNull(order.getLines())) {
			final OrderLineEntity lineEntity = mapper.map(line, OrderLineEntity.class);
			lineEntity.setParent(orderEntity);
			orderEntity.getLines().add(lineEntity);
		}
		orderEntity = orderService.save(orderEntity);
		wsOrdersController.sendOrderCreated(orderEntity.getId());

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'ORDERS'})")
	public ResponseEntity<Void> deleteOrder(Integer orderId) {
		orderService.deleteById(orderId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<OrderDetails> getOrder(Integer orderId) {
		final OrderEntity existingEntity = orderService.findById(orderId).orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(existingEntity, OrderDetails.class));
	}

	@Override
	public ResponseEntity<List<OrderSearchResult>> searchOrders(@Valid OrderSearchParameters body) {

		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final UserEntity user = userService.findByUsername(((JwtUser) auth.getPrincipal()).getUsername())
				.orElseThrow(NoSuchEntityException::new);

		final Optional<String> usernameParam = user.getType() == UserType.CUSTOMER ? Optional.of(user.getUsername())
				: Optional.ofNullable(null);

		final List<OrderSearchResult> orders = orderSearchService.findOrdersByParams(body, usernameParam);
		return ResponseEntity.ok(orders);
	}

	@org.springframework.transaction.annotation.Transactional
	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'ORDERS'})")
	public ResponseEntity<OrderDetails> updateOrder(@Valid OrderDetails body, Integer orderId) {

		final OrderEntity existingEntity = orderService.findById(orderId).orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("order.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		OrderDetails order;
		try {
			order = orderValidator.validate(body, request);
		} catch (final ValidatorException e) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("order.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage(e.getMessageKey(), e.getMessageParams(), request))
					.status(e.getError()).build();
		}

		final OrderEntity newEntity = mapper.map(order, OrderEntity.class);
		order.getLines().forEach(line -> {
			newEntity.getLines().add(mapper.map(line, OrderLineEntity.class));
		});
		newEntity.getLines().forEach(line -> line.setParent(newEntity));
		final OrderEntity orderEntity = orderService.save(newEntity);

		return ResponseEntity.ok(mapper.map(orderEntity, OrderDetails.class));
	}

}
