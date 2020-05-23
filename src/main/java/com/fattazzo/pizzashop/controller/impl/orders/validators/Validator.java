package com.fattazzo.pizzashop.controller.impl.orders.validators;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderDetails;

public interface Validator {

	OrderDetails execute(OrderDetails order) throws ValidatorException;

	int getOrder();

}
