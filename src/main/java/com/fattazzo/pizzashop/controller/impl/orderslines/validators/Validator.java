package com.fattazzo.pizzashop.controller.impl.orderslines.validators;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.api.OrderLine;

public interface Validator {

	OrderLine execute(OrderDetails order, OrderLine line, int lineNumber) throws ValidatorException;

	int getOrder();
}
