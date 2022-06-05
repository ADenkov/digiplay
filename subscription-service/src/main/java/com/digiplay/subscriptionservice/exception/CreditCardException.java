package com.digiplay.subscriptionservice.exception;

public class CreditCardException extends RuntimeException{
    private static final long serialVersionUID = -9096652953128464901L;

    public CreditCardException(final String e) {
        super(e);
    }
}
