package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model;

public enum TransactionStatus {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

    private final String value;

    // Constructor to set the value
    TransactionStatus(String value) {
        this.value = value;
    }

    // Getter for value
    public String getValue() {
        return value;
    }

    // Static method to get enum from String value
    public static TransactionStatus fromString(String value) {
        for (TransactionStatus status : TransactionStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
