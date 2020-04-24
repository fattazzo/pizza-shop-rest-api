package com.fattazzo.pizzashop.exception.security;

public class UserNotActiveException extends RuntimeException {

	private static final long serialVersionUID = -7733781818793505316L;

	public UserNotActiveException(String s) {
		super(s);
	}
}
