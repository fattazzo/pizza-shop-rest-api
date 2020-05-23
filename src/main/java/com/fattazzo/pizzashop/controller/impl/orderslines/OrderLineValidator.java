package com.fattazzo.pizzashop.controller.impl.orderslines;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.controller.impl.orderslines.validators.Validator;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.api.OrderLine;

@Component
public class OrderLineValidator {

	@Autowired
	private List<Validator> validators;

	public OrderDetails validate(OrderDetails order, HttpServletRequest request) throws ValidatorException {

		final List<Validator> orderedValidator = validators.stream()
				.sorted(Comparator.comparingInt(Validator::getOrder)).collect(Collectors.toList());

		for (int i = 0; i < order.getLines().size(); i++) {
			OrderLine line = order.getLines().get(i);

			for (final Validator validator : orderedValidator) {
				line = validator.execute(order, line, i + 1);
				order.getLines().set(i, line);
			}

		}

		return order;
	}

}
