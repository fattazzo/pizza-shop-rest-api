package com.fattazzo.pizzashop.controller.impl.orderslines.validators;

import java.math.BigDecimal;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.api.OrderLine;
import com.fattazzo.pizzashop.model.entity.ItemPizzaPriceEntity;
import com.fattazzo.pizzashop.model.entity.ItemProductPriceEntity;
import com.fattazzo.pizzashop.model.entity.ToppingExtraEntity;
import com.fattazzo.pizzashop.model.entity.VariationDoughEntity;
import com.fattazzo.pizzashop.service.item.ItemPizzaPriceService;
import com.fattazzo.pizzashop.service.item.ItemProductPriceService;
import com.fattazzo.pizzashop.service.variation.DoughService;
import com.fattazzo.pizzashop.service.variation.ToppingExtraService;

@Controller
@Qualifier("OrderLineTotalValidator")
public class OrderLineTotalValidator implements Validator {

	@Autowired
	private ItemProductPriceService itemProductPriceService;

	@Autowired
	private ItemPizzaPriceService itemPizzaPriceService;

	@Autowired
	private DoughService doughService;

	@Autowired
	private ToppingExtraService toppingExtraService;

	@Override
	public OrderLine execute(OrderDetails order, OrderLine line, int lineNumber) throws ValidatorException {

		if (line.getTotal() == null || line.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValidatorException("orderline.failed.total.notValid", new Object[] { lineNumber },
					HttpStatus.BAD_REQUEST);
		}

		// check total of all variation
		// product price
		BigDecimal totalCalculated = BigDecimal.ZERO;
		if (line.getProductPrice() != null && line.getProductPrice().getId() != null) {
			final ItemProductPriceEntity productPrice = itemProductPriceService.findById(line.getProductPrice().getId())
					.orElseGet(() -> ItemProductPriceEntity.builder().price(BigDecimal.ZERO).build());
			totalCalculated = totalCalculated.add(productPrice.getPrice());
		}
		// pizza price
		if (line.getPizzaPrice() != null && line.getPizzaPrice().getId() != null) {
			final ItemPizzaPriceEntity pizzaPrice = itemPizzaPriceService.findById(line.getPizzaPrice().getId())
					.orElseGet(() -> ItemPizzaPriceEntity.builder().price(BigDecimal.ZERO).build());
			totalCalculated = totalCalculated.add(pizzaPrice.getPrice());
		}
		// pizza dough
		if (line.getPizzaDough() != null && line.getPizzaDough().getId() != null) {
			final VariationDoughEntity dough = doughService.findById(line.getPizzaDough().getId())
					.orElseGet(() -> VariationDoughEntity.builder().extra(BigDecimal.ZERO).build());
			totalCalculated = totalCalculated.add(dough.getExtra());
		}
		// topping extras
		for (int i = 0; i < CollectionUtils.emptyIfNull(line.getPizzaToppingExtras()).size(); i++) {
			final ToppingExtraEntity toppingExtra = toppingExtraService
					.findById(line.getPizzaToppingExtras().get(i).getId())
					.orElseGet(() -> ToppingExtraEntity.builder().extra(BigDecimal.ZERO).build());
			totalCalculated = totalCalculated.add(toppingExtra.getExtra());
		}
		if (line.getTotal().compareTo(totalCalculated.multiply(new BigDecimal(line.getQuantity()))) != 0) {
			throw new ValidatorException("orderline.failed.total.notValid", new Object[] { lineNumber },
					HttpStatus.BAD_REQUEST);
		}

		return line;
	}

	@Override
	public int getOrder() {
		return 300;
	}

}
