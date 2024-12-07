package com.hcltest.abcmortgagebanking.abc_mortgage_banking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.Customer;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.CustomerService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
   
    @Autowired
    private CustomerService userService;

	/*
	 * @GetMapping("/{userId}") public List<Account> getAccounts(@PathVariable Long
	 * userId) { return accountService.getAccountsByUserId(userId); }
	 * 
	 * @PostMapping("/transfer") public void transferFunds(@RequestParam Long
	 * fromAccountId, @RequestParam Long toAccountId, @RequestParam Double amount) {
	 * accountService.transferFunds(fromAccountId, toAccountId, amount); }
	 */

    @PostMapping("/register")
    public Customer registerUser(@RequestBody Customer customer) {
        return userService.createUser(customer);
    }
    
    @GetMapping("/getCustomer")
    public Customer getCustomer(@RequestParam String username) {
        return userService.findByUsername(username);
    }
}