package com.fattazzo.pizzashop.controller.impl.orders.validators;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.Branch;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.entity.BranchEntity;
import com.fattazzo.pizzashop.service.branch.BranchService;

@Component
@Qualifier("OrderBranchValidator")
public class OrderBranchValidator implements Validator {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private BranchService branchService;

	private OrderDetails assignBranchFromDeliveryAddress(OrderDetails order) {
		if (order.getDeliveryAddress() != null && StringUtils.isNotBlank(order.getDeliveryAddress().getPostalCode())) {
			final List<BranchEntity> branches = branchService
					.findByShippingZonesName(order.getDeliveryAddress().getPostalCode());

			if (CollectionUtils.isNotEmpty(branches)) {
				order.setBranch(mapper.map(branches.get(0), Branch.class));
			}
		}
		return order;
	}

	private OrderDetails assignBranchPrimary(OrderDetails order) throws ValidatorException {
		final BranchEntity primaryBranch = branchService.loadPrimary().orElseThrow(
				() -> new ValidatorException("branch.failed.noPrimaryFound", null, HttpStatus.BAD_REQUEST));

		order.setBranch(mapper.map(primaryBranch, Branch.class));
		return order;
	}

	@Override
	public OrderDetails execute(OrderDetails order) throws ValidatorException {

		if (order.getBranch() == null) {
			order = assignBranchFromDeliveryAddress(order);
		}

		if (order.getBranch() == null || !order.getBranch().isEnabled()) {
			order = assignBranchPrimary(order);
		}

		return order;
	}

	@Override
	public int getOrder() {
		return 300;
	}

}
