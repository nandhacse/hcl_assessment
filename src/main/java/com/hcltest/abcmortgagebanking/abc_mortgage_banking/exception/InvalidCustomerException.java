package com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception;

public class InvalidCustomerException extends Exception {

    String message;

    public InvalidCustomerException(String message) {
        super(message);
        this.message = message;
    }

    public InvalidCustomerException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

}
