package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.impl;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.controller.CustomerController;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.InvalidCustomerException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.ServiceException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Account;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Customer;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Mortgage;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.AccountRepository;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.CustomerRepository;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Customer login(Long customerId, String password) throws InvalidCustomerException{
        logger.info("CustomerServiceImpl::login:: request details:: customerID:::"+customerId);
        try{
            return customerRepository.findByCustomerIdAndPassword(customerId, password);
        } catch (Exception e) {
            logger.info("Invalid customer id :: {0}:: "+ customerId);
            throw new InvalidCustomerException(e.getMessage());
        }
    }

    @Override
    public List<Account> getAccountDetailsByCustomerId(Long customerId) {
        logger.info("CustomerServiceImpl::getCustomerAccountDetailsByCustomerId:: request details:: customerID:::"+customerId);
        return accountRepository.findByCustomerId(customerId);
    }
}
