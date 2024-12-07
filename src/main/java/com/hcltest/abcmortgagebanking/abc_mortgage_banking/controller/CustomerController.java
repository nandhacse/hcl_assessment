package com.hcltest.abcmortgagebanking.abc_mortgage_banking.controller;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Customer;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.LoginRequest;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getCustomerId() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body("Customer ID and password are required");
        }

        try {
            Customer customer = customerService.login(
                    loginRequest.getCustomerId(),
                    loginRequest.getPassword()
            );
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
