package com.fattazzo.pizzashop.exception.security;

public class RefreshTokenInBlackList extends RuntimeException {
	private static final long serialVersionUID = -7249320653224524988L;

	public RefreshTokenInBlackList(String s) {
		super(s);
	}
}
