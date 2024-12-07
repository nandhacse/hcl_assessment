package com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByCustomerIdAndPassword(Long customerId, String password);
}
