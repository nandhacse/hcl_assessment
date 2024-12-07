package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> findAll();

    Account save(Account account);

    Optional<Account> getAccount(Long id);

    Account deposit(Long id, double amount);

    Account withdraw(Long id, double amount);

    Account transfer(Account from, Account to, double amount);



}
