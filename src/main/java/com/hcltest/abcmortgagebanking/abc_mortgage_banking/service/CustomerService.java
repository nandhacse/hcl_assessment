package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    public Customer login(Long customerId, String password);
}
