package com.fattazzo.pizzashop.controller.impl.orders.validators;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderDetails;

@Controller
@Qualifier("OrderTotalValidator")
public class OrderTotalValidator implements Validator {

	@Override
	public OrderDetails execute(OrderDetails order) throws ValidatorException {
		if (order.getTotal() == null) {
			throw new ValidatorException("order.failed.noTotal", null, HttpStatus.BAD_REQUEST);
		}

		final BigDecimal totalLines = order.getLines().stream().map(line -> line.getTotal()).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		if (order.getTotal().compareTo(totalLines) != 0) {
			throw new ValidatorException("order.failed.linesTotal",
					new Object[] { totalLines.toPlainString(), order.getTotal().toPlainString() },
					HttpStatus.BAD_REQUEST);
		}

		return order;
	}

	@Override
	public int getOrder() {
		return 600;
	}

}
