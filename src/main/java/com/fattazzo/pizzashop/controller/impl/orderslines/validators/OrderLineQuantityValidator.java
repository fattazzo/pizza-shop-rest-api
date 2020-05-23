package com.fattazzo.pizzashop.controller.impl.orderslines.validators;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.api.OrderLine;

@Controller
@Qualifier("OrderLineQuantityValidator")
public class OrderLineQuantityValidator implements Validator {

	@Override
	public OrderLine execute(OrderDetails order, OrderLine line, int lineNumber) throws ValidatorException {

		if (line.getQuantity() == null || line.getQuantity() < 0) {
			throw new ValidatorException("orderline.failed.quantity.notValid", new Object[] { lineNumber },
					HttpStatus.BAD_REQUEST);
		}

		return line;
	}

	@Override
	public int getOrder() {
		return 100;
	}

}
