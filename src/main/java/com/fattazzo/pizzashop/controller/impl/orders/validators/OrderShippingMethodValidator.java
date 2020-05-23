package com.fattazzo.pizzashop.controller.impl.orders.validators;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.entity.BranchEntity;
import com.fattazzo.pizzashop.model.entity.ShippingMethodEntity;
import com.fattazzo.pizzashop.model.entity.ShippingMethodType;
import com.fattazzo.pizzashop.service.branch.BranchService;
import com.fattazzo.pizzashop.service.shippingmethod.ShippingMethodService;

@Component
@Qualifier("OrderShippingMethodValidator")
public class OrderShippingMethodValidator implements Validator {

	@Autowired
	private ShippingMethodService shippingMethodService;

	@Autowired
	private BranchService branchService;

	@Override
	public OrderDetails execute(OrderDetails order) throws ValidatorException {
		// check if shipping method exist
		final ShippingMethodEntity method = shippingMethodService.findById(order.getShippingMethod().getId())
				.orElseThrow(() -> new ValidatorException("shippingMethod.notFound", null, HttpStatus.BAD_REQUEST));

		// check if shipping method is enable
		if (!method.isEnabled()) {
			throw new ValidatorException("shippingMethod.notEnable", new Object[] { method.getTitle() },
					HttpStatus.BAD_REQUEST);
		}

		// check if branch support shipping method
		final BranchEntity branch = branchService.findById(order.getBranch().getId()).orElse(new BranchEntity());
		if (!CollectionUtils.emptyIfNull(branch.getShippingMethods()).contains(method)) {
			throw new ValidatorException("shippingMethod.notManaged", new Object[] { method.getTitle() },
					HttpStatus.BAD_REQUEST);
		}

		// check transactionID on type PayPal
		if (method.getType() == ShippingMethodType.PAY_PAL && StringUtils.isBlank(order.getTransactionId())) {
			throw new ValidatorException("shippingMethod.transactionIdRequired", new Object[] { method.getTitle() },
					HttpStatus.BAD_REQUEST);
		}

		// check il shipping method support shipping type
		if (!method.getShippingTypes().contains(order.getShippingType())) {
			throw new ValidatorException("shippingMethod.shippingTypeNotSupported", new Object[] { method.getTitle() },
					HttpStatus.BAD_REQUEST);
		}

		return order;
	}

	@Override
	public int getOrder() {
		return 400;
	}

}
