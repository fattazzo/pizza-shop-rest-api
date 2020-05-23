package com.fattazzo.pizzashop.controller.impl.orders.validators;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions.ValidatorException;
import com.fattazzo.pizzashop.model.api.OrderDetails;
import com.fattazzo.pizzashop.model.entity.UserEntity;
import com.fattazzo.pizzashop.security.JwtUser;
import com.fattazzo.pizzashop.service.user.UserService;

@Component
@Qualifier("OrderUserValidator")
public class OrderUserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public OrderDetails execute(OrderDetails order) throws ValidatorException {
		// check if order user exist
		final UserEntity user = userService.findByUsername(order.getCustomer().getUsername())
				.orElseThrow(() -> new ValidatorException("user.notFound",
						new Object[] { order.getCustomer().getUsername() }, HttpStatus.BAD_REQUEST));

		// check if logged user is order user
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!Objects.equals(((JwtUser) auth.getPrincipal()).getUsername(), user.getUsername())) {
			throw new ValidatorException("order.insert.failed.wrongUser",
					new Object[] { ((JwtUser) auth.getPrincipal()).getUsername() }, HttpStatus.BAD_REQUEST);
		}
		return order;
	}

	@Override
	public int getOrder() {
		return 100;
	}

}
