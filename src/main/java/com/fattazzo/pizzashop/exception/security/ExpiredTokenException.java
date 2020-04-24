package com.fattazzo.pizzashop.exception.security;

public class ExpiredTokenException extends RuntimeException {
	private static final long serialVersionUID = -6523428152344961287L;

	public ExpiredTokenException(String s) {
		super(s);
	}
}
