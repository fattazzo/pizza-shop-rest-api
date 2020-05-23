package com.fattazzo.pizzashop.controller.impl.orderslines;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.model.api.Item;
import com.fattazzo.pizzashop.model.api.ItemPizzaPrice;
import com.fattazzo.pizzashop.model.api.ItemProductPrice;
import com.fattazzo.pizzashop.model.api.OrderLine;
import com.fattazzo.pizzashop.model.api.OrderLineRequest;
import com.fattazzo.pizzashop.model.api.ToppingExtra;
import com.fattazzo.pizzashop.model.api.VariationDough;

@Component
public class OrderLineMapper {

	public OrderLine toOrderLine(OrderLineRequest lineRequest) {
		final OrderLine line = new OrderLine();
		line.setCustomerNote(lineRequest.getCustomerNote());
		line.setItem(new Item().id(lineRequest.getItemId()));
		line.setPizzaDough(new VariationDough().id(lineRequest.getPizzaDoughId()));
		line.setPizzaPrice(new ItemPizzaPrice().id(lineRequest.getPizzaPriceId()));
		CollectionUtils.emptyIfNull(lineRequest.getPizzaToppingExtrasId())
				.forEach(id -> line.addPizzaToppingExtrasItem(new ToppingExtra().id(id)));
		line.setProductPrice(new ItemProductPrice().id(lineRequest.getProductPriceId()));
		line.setQuantity(lineRequest.getQuantity());
		line.setTotal(lineRequest.getTotal());
		return line;
	}
}
