package com.hcltest.abcmortgagebanking.abc_mortgage_banking.controller;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Account;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/account/")
public class AccountController {

    private static final Logger logger = Logger.getLogger(AccountController.class.getName());

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        logger.info("start fetching all accounts");
        try {
            List<Account> accounts = accountService.findAll();

            logger.info("end fetching all accounts");

            if(accounts == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        logger.info("start creating account");
        if(account == null) {
            throw new IllegalArgumentException("account is null");
        }
        Account account1 = accountService.save(account);
        return ResponseEntity.ok(account1);
    }
}
