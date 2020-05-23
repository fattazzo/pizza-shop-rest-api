package com.fattazzo.pizzashop.controller.impl.orders.validators;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.service.settings.SettingsService;

@Component
@Qualifier("OrderDateRequestValidator")
public class OrderDateRequestValidator implements Validator {

	@Autowired
	private SettingsService settingsService;

	@Override
	public OrderDetails execute(OrderDetails order) throws ValidatorException {
		// check date request range
		final long minutes = Duration.between(order.getDateCreation(), order.getDateRequest()).toMinutes();
		final Integer minMinutes = settingsService.load().orElseThrow(
				() -> new ValidatorException("settings.failed.notFound", null, HttpStatus.INTERNAL_SERVER_ERROR))
				.getMinOrderRequestMinutes();
		if (minutes < minMinutes.intValue()) {
			throw new ValidatorException("order.insert.failed.minOrderRequestMinutes", new Object[] { minMinutes },
					HttpStatus.BAD_REQUEST);
		}
		return order;
	}

	@Override
	public int getOrder() {
		return 200;
	}

}
