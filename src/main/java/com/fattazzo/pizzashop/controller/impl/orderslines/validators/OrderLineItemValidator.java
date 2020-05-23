package com.fattazzo.pizzashop.controller.impl.orderslines.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.api.OrderLine;
import com.fattazzo.pizzashop.model.entity.ItemEntity;
import com.fattazzo.pizzashop.model.entity.ItemPizzaEntity;
import com.fattazzo.pizzashop.model.entity.ItemProductEntity;
import com.fattazzo.pizzashop.service.item.ItemService;

@Component
@Qualifier("OrderLineItemValidator")
public class OrderLineItemValidator implements Validator {

	@Autowired
	private ItemService itemService;

	@Autowired
	private OrderLinePizzaValidator orderLinePizzaValidator;

	@Autowired
	private OrderLineProductValidator orderLineProductValidator;

	@Override
	public OrderLine execute(OrderDetails order, OrderLine line, int lineNumber) throws ValidatorException {

		// check if exist item on line
		if (line.getItem() == null || line.getItem().getId() == null) {
			throw new ValidatorException("orderline.failed.noItem", new Object[] { lineNumber },
					HttpStatus.BAD_REQUEST);
		}

		// check if item exist
		final ItemEntity item = itemService.findById(line.getItem().getId())
				.orElseThrow(() -> new ValidatorException("orderline.failed.noItem", new Object[] { lineNumber },
						HttpStatus.BAD_REQUEST));

		if (item instanceof ItemProductEntity) {
			line = orderLineProductValidator.validateProductVariation(line, lineNumber, (ItemProductEntity) item);
		} else {
			line = orderLinePizzaValidator.validatePizzaVariation(line, lineNumber, (ItemPizzaEntity) item);
		}

		return line;
	}

	@Override
	public int getOrder() {
		return 200;
	}

}
