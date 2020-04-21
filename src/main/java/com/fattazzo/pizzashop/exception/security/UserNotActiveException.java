package com.fattazzo.pizzashop.exception.security;

public class UserNotActiveException extends RuntimeException {
    public UserNotActiveException(String s) {
        super(s);
    }
}
