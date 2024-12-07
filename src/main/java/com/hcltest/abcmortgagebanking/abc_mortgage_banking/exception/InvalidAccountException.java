package com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception;

public class InvalidAccountException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidAccountException() {
        super("Invalid account id");
    }

    public InvalidAccountException(String message) {
        super(message);
    }
}
