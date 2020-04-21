package com.fattazzo.pizzashop.exception.security;

public class RefreshTokenInBlackList  extends RuntimeException{
    public RefreshTokenInBlackList(String s) {
        super(s);
    }
}
