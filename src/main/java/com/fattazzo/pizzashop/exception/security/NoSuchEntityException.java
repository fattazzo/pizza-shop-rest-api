package com.fattazzo.pizzashop.exception.security;

public class NoSuchEntityException extends RuntimeException{

    public NoSuchEntityException() {
        super();
    }

    public NoSuchEntityException(String s)
    {
        super(s);
    }

    public NoSuchEntityException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public NoSuchEntityException(Throwable throwable)
    {
        super(throwable);
    }
}
