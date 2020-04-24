package com.fattazzo.pizzashop.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.fattazzo.pizzashop.controller.ShippingmethodsApi;
import com.fattazzo.pizzashop.exception.security.NoSuchEntityException;
import com.fattazzo.pizzashop.exception.security.RestException;
import com.fattazzo.pizzashop.model.dto.data.ShippingMethod;
import com.fattazzo.pizzashop.model.entity.data.ShippingMethodEntity;
import com.fattazzo.pizzashop.service.local.LocaleUtilsMessage;
import com.fattazzo.pizzashop.service.shippingmethod.ShippingMethodService;

@RestController
public class ShippingmethodsController implements ShippingmethodsApi {

	@Autowired
	private ShippingMethodService shippingMethodService;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	LocaleUtilsMessage localeUtilsMessage;

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
	public ResponseEntity<List<ShippingMethod>> getShippingMethods() {
		final List<ShippingMethod> shippingMethods = shippingMethodService.findAll().stream()
				.map(sm -> mapper.map(sm, ShippingMethod.class)).collect(Collectors.toList());
		return ResponseEntity.ok(shippingMethods);
	}

	@Override
	@PreAuthorize("@securityService.hasAnyPermission({'SHIPPING_METHODS'})")
	public ResponseEntity<ShippingMethod> updateShippingMethod(@Valid ShippingMethod body, Integer shippingmethodId) {
		final ShippingMethodEntity existingEntity = shippingMethodService.findById(shippingmethodId)
				.orElseThrow(NoSuchEntityException::new);

		if (!existingEntity.getId().equals(body.getId())) {
			throw RestException.newBuilder()
					.title(localeUtilsMessage.getErrorLocalizedMessage("shippingmethod.update.failed.title", null))
					.detail(localeUtilsMessage.getErrorLocalizedMessage("shippingmethod.update.failed.idParamNotEquals",
							null))
					.status(HttpStatus.BAD_REQUEST).build();
		}

		final ShippingMethodEntity entity = shippingMethodService.save(mapper.map(body, ShippingMethodEntity.class));
		return ResponseEntity.ok(mapper.map(entity, ShippingMethod.class));
	}

}
