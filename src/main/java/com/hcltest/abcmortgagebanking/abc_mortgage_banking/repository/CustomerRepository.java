package com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
   Customer	findByUsername(String username);
}