package com.fattazzo.pizzashop.controller.impl.orderslines.validators;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderLine;
import com.fattazzo.pizzashop.model.api.ToppingExtra;
import com.fattazzo.pizzashop.model.entity.ItemPizzaEntity;
import com.fattazzo.pizzashop.model.entity.ItemPizzaPriceEntity;
import com.fattazzo.pizzashop.model.entity.ToppingExtraEntity;
import com.fattazzo.pizzashop.model.entity.VariationDoughEntity;
import com.fattazzo.pizzashop.service.variation.ToppingExtraService;

@Component
public class OrderLinePizzaValidator {

	@Autowired
	private ToppingExtraService toppingExtraService;

	public OrderLine validatePizzaPrice(OrderLine line, int lineNumber, ItemPizzaEntity pizza)
			throws ValidatorException {

		// check if price is present
		if (line.getPizzaPrice() == null || line.getPizzaPrice().getId() == null) {
			throw new ValidatorException("orderline.failed.pizza.noPrice", new Object[] { pizza.getName(), lineNumber },
					HttpStatus.BAD_REQUEST);
		}

		// check if price exist
		final ItemPizzaPriceEntity pizzaPrice = pizza.getPrices().stream()
				.filter(p -> p.getId().compareTo(line.getPizzaPrice().getId()) == 0).findFirst()
				.orElseThrow(() -> new ValidatorException("orderline.failed.pizza.priceNotValid",
						new Object[] { pizza.getName(), lineNumber }, HttpStatus.BAD_REQUEST));

		// check if price variation is enable
		if (!pizzaPrice.getVariationSize().isEnabled()) {
			throw new ValidatorException("orderline.failed.pizza.priceVariationNotEnable",
					new Object[] { pizza.getName(), lineNumber, pizzaPrice.getVariationSize().getName() },
					HttpStatus.BAD_REQUEST);
		}

		return line;
	}

	public OrderLine validatePizzaVariation(OrderLine line, int lineNumber, ItemPizzaEntity pizza)
			throws ValidatorException {

		line = validatePizzaPrice(line, lineNumber, pizza);
		line = validateVariationDough(line, lineNumber, pizza);
		line = validateToppingExtra(line, lineNumber, pizza);

		return line;
	}

	public OrderLine validateToppingExtra(OrderLine line, int lineNumber, ItemPizzaEntity pizza)
			throws ValidatorException {

		for (final ToppingExtra toppingExtra : CollectionUtils.emptyIfNull(line.getPizzaToppingExtras())) {

			// check if exist
			final ToppingExtraEntity toppingExtraEntity = toppingExtraService.findById(toppingExtra.getId())
					.orElseThrow(() -> new ValidatorException("orderline.failed.pizza.toppingExtraNotFound",
							new Object[] { pizza.getName(), lineNumber }, HttpStatus.BAD_REQUEST));

			// check if dough, size and topping are enable
			if (!toppingExtraEntity.getDough().isEnabled() || !toppingExtraEntity.getVariationSize().isEnabled()
					|| !toppingExtraEntity.getTopping().isEnabled()) {
				throw new ValidatorException("orderline.failed.pizza.toppingExtraNotEnabled",
						new Object[] { pizza.getName(), lineNumber, toppingExtraEntity.getTopping().getName() },
						HttpStatus.BAD_REQUEST);
			}
		}

		return line;
	}

	public OrderLine validateVariationDough(OrderLine line, int lineNumber, ItemPizzaEntity pizza)
			throws ValidatorException {

		// check if present
		if (line.getPizzaDough() == null || line.getPizzaDough().getId() == null) {
			throw new ValidatorException("orderline.failed.pizza.noDough", new Object[] { lineNumber },
					HttpStatus.BAD_REQUEST);
		}

		// check if exist
		final VariationDoughEntity doughEntity = pizza.getCategory().getDoughs().stream()
				.filter(d -> d.getId().compareTo(line.getPizzaDough().getId()) == 0).findFirst()
				.orElseThrow(() -> new ValidatorException("orderline.failed.pizza.doughNotValid",
						new Object[] { pizza.getName(), lineNumber }, HttpStatus.BAD_REQUEST));

		// check if variation is enable
		if (!doughEntity.isEnabled()) {
			throw new ValidatorException("orderline.failed.pizza.doughVariationNotEnable",
					new Object[] { pizza.getName(), lineNumber, doughEntity.getName() }, HttpStatus.BAD_REQUEST);
		}

		return line;
	}

}
