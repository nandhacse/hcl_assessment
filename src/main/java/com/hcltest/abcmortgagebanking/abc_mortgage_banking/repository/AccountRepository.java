package com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Finds an account by its account number.
     *
     * @param accountNumber the account number to search for.
     * @return the Account object if found, or null if not found.
     */
    Account findByAccountNumber(String accountNumber);
    List<Account> findAll();

    List<Account> findByCustomerId(Long customerId);
}
