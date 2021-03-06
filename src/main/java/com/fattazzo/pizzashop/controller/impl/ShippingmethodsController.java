package com.fattazzo.pizzashop.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.api.ShippingmethodsApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.api.ShippingMethod;
import com.fattazzo.pizzashop.model.entity.ShippingMethodEntity;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.shippingmethod.ShippingMethodService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = { "shippingmethods" })
public class ShippingmethodsController implements ShippingmethodsApi {

	@Autowired
	private ShippingMethodService shippingMethodService;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

	private final HttpServletRequest request;

	@Autowired
	public ShippingmethodsController(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SHIPPING_METHODS'})")
	public ResponseEntity<ShippingMethod> createShippingMethod(@Valid ShippingMethod body) {

		ShippingMethodEntity entity = mapper.map(body, ShippingMethodEntity.class);

		entity = shippingMethodService.save(entity);

		return new ResponseEntity<>(mapper.map(entity, ShippingMethod.class), HttpStatus.CREATED);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SHIPPING_METHODS'})")
	public ResponseEntity<Void> deleteShippingMethod(Integer shippingmethodId) {
		shippingMethodService.deleteById(shippingmethodId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<ShippingMethod> getShippingMethod(Integer shippingmethodId) {
		final ShippingMethodEntity entity = shippingMethodService.findById(shippingmethodId)
				.orElseThrow(NoSuchEntityException::new);
		return ResponseEntity.ok(mapper.map(entity, ShippingMethod.class));
	}

	@Override
	public ResponseEntity<List<ShippingMethod>> getShippingMethods(@Valid Boolean includeDisabled) {
		final List<ShippingMethodEntity> entities = (BooleanUtils.isTrue(includeDisabled))
				? shippingMethodService.findAll()
				: shippingMethodService.findAllEnabled();

		final List<ShippingMethod> shippingMethods = entities.stream().map(s -> mapper.map(s, ShippingMethod.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok(shippingMethods);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SHIPPING_METHODS'})")
	public ResponseEntity<ShippingMethod> updateShippingMethod(@Valid ShippingMethod body, Integer shippingmethodId) {
		final ShippingMethodEntity existingEntity = shippingMethodService.findById(shippingmethodId)
				.orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getMessage("shippingmethod.update.failed.title", null, request))
					.detail(localeUtilsMessage.getMessage("idParamNotEquals", null, request))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final ShippingMethodEntity entity = shippingMethodService.save(mapper.map(body, ShippingMethodEntity.class));
		return ResponseEntity.ok(mapper.map(entity, ShippingMethod.class));
	}

}
