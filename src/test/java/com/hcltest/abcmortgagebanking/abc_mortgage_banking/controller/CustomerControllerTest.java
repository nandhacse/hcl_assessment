package com.hcltest.abcmortgagebanking.abc_mortgage_banking.controller;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Account;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Customer;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.LoginRequest;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void When_ValidaRequestPassed_Expect_SuccessResponse() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCustomerId(1001L);
        loginRequest.setPassword("password123");
        when(customerService.login(any(), any())).thenReturn(buildCustomerResponse());
        ResponseEntity<Object> response = customerController.login(loginRequest);
        assertNotNull(response);
    }

    @Test
    void When_NullRequestValuesPassed_Expect_ErrorMessageResponse() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCustomerId(null);
        loginRequest.setPassword(null);
        ResponseEntity<Object> response = customerController.login(loginRequest);
        Assertions.assertEquals("Customer ID and password are required", response.getBody());
    }

    @Test
    void When_ValidCustomerIdPassed_Expect_SuccessResponse() {
        Long customerId=1001L;
        when(customerService.getAccountDetailsByCustomerId(any())).thenReturn(buildAccountDetails());
        ResponseEntity<List<Account>> response = customerController.getAccountDetailsByCustomerId(customerId);
        assertNotNull(response);
    }

    private List<Account> buildAccountDetails() {
        List<Account> accountList=new ArrayList<>();
        Account account=new Account();
        account.setAccountId(1);
        account.setAccountNumber("1234");
        account.setCustomerId(1001);
        accountList.add(account);
        Account account2=new Account();
        account2.setAccountId(2);
        account2.setAccountNumber("4567");
        account2.setCustomerId(1002);
        accountList.add(account2);
        return accountList;
    }

    private Customer buildCustomerResponse() {
        Customer customer = new Customer();
        customer.setCustomerId(1001L);
        customer.setName("Test");
        return customer;
    }


}
