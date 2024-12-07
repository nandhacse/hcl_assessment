package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model;

public enum AccountType {
    SAVINGS("SAVINGS"),
    CURRENT("CURRENT"),
    MORTGAGE("MORTGAGE");

    private final String value;

    // Constructor
    AccountType(String value) {
        this.value = value;
    }

    // Getter for value
    public String getValue() {
        return value;
    }

    // Static method to get enum from String value
    public static AccountType fromString(String value) {
        for (AccountType type : AccountType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}