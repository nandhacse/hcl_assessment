package com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAll();
}
