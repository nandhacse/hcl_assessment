package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request;

public record TransferRequest(String fromAccountId, String toAccountId, String amount, String remarks) {
}
