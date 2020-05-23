package com.fattazzo.pizzashop.controller.impl.orders.validators;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderDetails;

@Controller
@Qualifier("OrderLinesValidator")
public class OrderLinesValidator implements Validator {

	@Override
	public OrderDetails execute(OrderDetails order) throws ValidatorException {
		if (CollectionUtils.isEmpty(order.getLines())) {
			throw new ValidatorException("order.failed.noLines", null, HttpStatus.BAD_REQUEST);
		}
		return order;
	}

	@Override
	public int getOrder() {
		return 500;
	}

}
