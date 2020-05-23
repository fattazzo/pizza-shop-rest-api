package com.fattazzo.pizzashop.controller.impl.orderslines.validators;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderLine;
import com.fattazzo.pizzashop.model.entity.ItemProductEntity;
import com.fattazzo.pizzashop.model.entity.ItemProductPriceEntity;

@Component
public class OrderLineProductValidator {

	public OrderLine validateProductVariation(OrderLine line, int lineNumber, ItemProductEntity product)
			throws ValidatorException {
		// check if price is present
		if (line.getProductPrice() == null || line.getProductPrice().getId() == null) {
			throw new ValidatorException("orderline.failed.product.noPrice",
					new Object[] { product.getName(), lineNumber }, HttpStatus.BAD_REQUEST);
		}

		// check if price exist
		final ItemProductPriceEntity productPrice = product.getPrices().stream()
				.filter(p -> p.getId().compareTo(line.getProductPrice().getId()) == 0).findFirst()
				.orElseThrow(() -> new ValidatorException("orderline.failed.product.priceNotValid",
						new Object[] { product.getName(), lineNumber }, HttpStatus.BAD_REQUEST));

		// check if price variation is enable
		if (!productPrice.getVariation().isEnabled()) {
			throw new ValidatorException("orderline.failed.product.priceVariationNotEnable",
					new Object[] { product.getName(), lineNumber, productPrice.getVariation().getName() },
					HttpStatus.BAD_REQUEST);
		}

		return line;
	}

}
