package com.hcltest.abcmortgagebanking.abc_mortgage_banking.controller;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.InvalidCustomerException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Account;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Customer;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.LoginRequest;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());
    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Entered into CustomerController::loin:: Request :: customerId::" + loginRequest.getCustomerId());
        if (loginRequest.getCustomerId() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body("Customer ID and password are required");
        }
        Customer response = null;
        try {
            response = customerService.login(loginRequest.getCustomerId(),loginRequest.getPassword());
            if(response==null){
                return ResponseEntity.status(401).body("Invalid Customer Id ..No Data found for given customer id::"+loginRequest.getCustomerId());
            }
        } catch (InvalidCustomerException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
        logger.info("Response CustomerController::login:: response :: customer::" + response.getCustomerId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountDetailsByCustomerId(@PathVariable Long customerId) {
        logger.info("Entered into CustomerController::getCustomerAccountDetailsByCustomerId:: Request :: customerId::" + customerId);
        try {
            List<Account> accounts = customerService.getAccountDetailsByCustomerId(customerId);
            if (accounts == null) {
                logger.info("No Data found for given customerId::" + customerId);
                return ResponseEntity.noContent().build();
            }
            logger.info("Response CustomerController::getCustomerAccountDetailsByCustomerId:: response :: customer::" + accounts.get(0).getAccountNumber());
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
