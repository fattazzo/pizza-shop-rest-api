package com.fattazzo.pizzashop.controller.impl.orders.validators.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidatorException extends Exception {

	private static final long serialVersionUID = 2316178998193721730L;

	private final String messageKey;
	private final Object[] messageParams;

	private final HttpStatus error;

}
