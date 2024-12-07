package com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Mortgage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MortgageRepository extends JpaRepository<Mortgage, Long> {


    @Query("select m from Mortgage m where m.mortgageAccountNumber = ?1 and m.password = ?2")
    Mortgage findByMortgageAccountNumberAndPassword(String mortgageNumber, String password);
}
