package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.impl;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Customer;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.CustomerRepository;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer login(Long customerId, String password) {
        // Find customer by ID and password (hashed in production)
        return customerRepository.findByCustomerIdAndPassword(customerId, password)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
