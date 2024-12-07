package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.Customer;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository userRepository;

    public Customer createUser(Customer user) {
        return userRepository.save(user);
    }

    public Customer findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}