package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model;

public enum AccountStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    CLOSED("CLOSED");

    private final String value;

    // Constructor
    AccountStatus(String value) {
        this.value = value;
    }

    // Getter for value
    public String getValue() {
        return value;
    }

    // Static method to get enum from String value
    public static AccountStatus fromString(String value) {
        for (AccountStatus status : AccountStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
