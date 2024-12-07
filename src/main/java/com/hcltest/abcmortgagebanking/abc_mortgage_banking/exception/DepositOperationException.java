package com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception;

public class DepositOperationException extends Exception {

    private static final long serialVersionUID = 1L;

    public DepositOperationException() {
        super("Deposit operation failed");
    }

    public DepositOperationException(String message) {
        super(message);
    }
}