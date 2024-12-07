package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model;

public enum TransactionType {
    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL"),
    TRANSFER("TRANSFER");

    private final String type;

    // Constructor
    TransactionType(String type) {
        this.type = type;
    }

    // Getter for type
    public String getType() {
        return type;
    }
}
