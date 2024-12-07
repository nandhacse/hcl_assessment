package com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception;

public class ServiceException extends Exception {
    String message;

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

}
