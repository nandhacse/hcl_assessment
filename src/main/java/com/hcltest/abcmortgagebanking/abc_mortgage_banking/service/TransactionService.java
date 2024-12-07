package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.DepositOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.InvalidAccountException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.TransferOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.WithdrawOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Transaction;

import java.util.List;

public interface TransactionService {

    void deposit(Long accountId, double amount, String remarks) throws InvalidAccountException, DepositOperationException;

    void withdraw(Long accountId, double amount, String remarks) throws InvalidAccountException, WithdrawOperationException;

    void transfer(Long fromAccountId, Long toAccountId, double amount, String remarks) throws InvalidAccountException, TransferOperationException;

    List<Transaction> getTransaction(String accountId);
}
