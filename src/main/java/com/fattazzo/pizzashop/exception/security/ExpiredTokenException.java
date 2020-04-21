package com.fattazzo.pizzashop.exception.security;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException(String s) {
        super(s);
    }
}
