package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.impl;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.InvalidCustomerException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Customer;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void When_validRequestPayloadIsSent_Expect_ReturnAllTheMatchingLocations() throws InvalidCustomerException {
        Long customerId=1001L;
        String password="password123";
        Mockito.when(customerRepository.findByCustomerIdAndPassword(any(),any())).thenReturn(buildCustomerResponse());
        Customer customer = customerService.login(customerId, password);
        Assertions.assertEquals("Test", customer.getName());
    }

    private Customer buildCustomerResponse() {
        Customer customer=new Customer();
        customer.setCustomerId(1001L);
        customer.setName("Test");
        return customer;
    }
}


