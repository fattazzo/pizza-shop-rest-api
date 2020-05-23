package com.fattazzo.pizzashop.controller.impl.orders;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.controller.impl.orders.validators.Validator;
import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.controller.impl.orderslines.OrderLineValidator;
import com.fattazzo.pizzashop.model.api.OrderDetails;

@Component
public class OrderValidator {

	@Autowired
	private List<Validator> validators;

	@Autowired
	private OrderLineValidator orderLineValidator;

	public OrderDetails validate(OrderDetails order, HttpServletRequest request) throws ValidatorException {

		final List<Validator> orderedValidator = validators.stream()
				.sorted(Comparator.comparingInt(Validator::getOrder)).collect(Collectors.toList());

		// order lines validator
		order = orderLineValidator.validate(order, request);

		// order validation
		for (final Validator validator : orderedValidator) {
			order = validator.execute(order);
		}

		return order;
	}
}
