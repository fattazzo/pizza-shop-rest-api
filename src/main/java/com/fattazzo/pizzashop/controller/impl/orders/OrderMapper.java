package com.fattazzo.pizzashop.controller.impl.orders;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.controller.impl.orderslines.OrderLineMapper;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.api.OrderRequest;
import com.fattazzo.pizzashop.model.api.ShippingMethod;
import com.fattazzo.pizzashop.model.api.User;

@Component
public class OrderMapper {

	@Autowired
	private OrderLineMapper orderLineMapper;

	public OrderDetails toOrderDetails(OrderRequest orderRequest, String username) {
		final OrderDetails orderDetails = new OrderDetails();
		orderDetails.setBackofficeNote(orderRequest.getBackofficeNote());
		orderDetails.setCustomer(new User().username(username));
		orderDetails.setCustomerNote(orderRequest.getCustomerNote());
		orderDetails.setDateCreation(orderRequest.getDateCreation());
		orderDetails.setDateRequest(orderRequest.getDateRequest());
		orderDetails.setDateRequestConfirmed(orderRequest.getDateRequestConfirmed());
		orderDetails.setDeliveryAddress(orderRequest.getDeliveryAddress());
		orderDetails.setShippingMethod(new ShippingMethod().id(orderRequest.getShippingMethodId()));
		orderDetails.setShippingType(orderRequest.getShippingType());
		orderDetails.setTotal(orderRequest.getTotal());
		orderDetails.setTransactionId(orderRequest.getTransactionId());

		CollectionUtils.emptyIfNull(orderRequest.getLines())
				.forEach(line -> orderDetails.addLinesItem(orderLineMapper.toOrderLine(line)));

		return orderDetails;
	}
}
