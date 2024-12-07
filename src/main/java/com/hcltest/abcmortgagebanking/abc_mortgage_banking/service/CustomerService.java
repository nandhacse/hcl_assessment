package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Customer;

public interface CustomerService {
    public Customer login(Long customerId, String password);
}
