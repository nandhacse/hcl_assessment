package com.hcltest.abcmortgagebanking.abc_mortgage_banking.controller;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.ServiceException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Mortgage;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.MortgageRequst;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.MortgageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class MortgageController {
    private static final Logger LOGGER = Logger.getLogger(MortgageController.class.getName());

    private MortgageService mortgageService;

    public MortgageController(MortgageService mortgageService) {
        this.mortgageService = mortgageService;
    }

    @PostMapping(value = "/createMortgage")
    public String createMortgage(@RequestBody Mortgage mortgage) {
        LOGGER.info("create mortgage started with request {0}:: "+ mortgage);
        String response = null;
        try{
            if(Objects.isNull(mortgage)) {
                throw new ServiceException("Failed to create a mortgage for the customerId {0} "+ mortgage.getCustomerId());
            }
            response = mortgageService.save(mortgage);
        } catch (ServiceException e) {
            response = e.getMessage();
        }
        LOGGER.info("create mortgage ended successfully for request {0}:: "+ mortgage);
        return response;
    }

    @PostMapping(value = "/loginUsingMortgage")
    public String loginUsingMortgage(@RequestBody MortgageRequst mortgage) {
        LOGGER.info("login using mortgage started with request {0}:: "+ mortgage);
        String response = null;
        try{
            if(Objects.isNull(mortgage)) {
                LOGGER.info("Failed to login with mortgage for the mortgage Account Number {0} "+ mortgage.getMortgageNumber());
                throw new ServiceException("Failed to login with mortgage for the mortgage Account Number {0} "+ mortgage.getMortgageNumber());
            }
            response = mortgageService.loginUsingMortgage(mortgage);
        } catch (ServiceException e) {
            response = e.getMessage();
        }
        LOGGER.info("login using mortgage ended successfully for request {0}:: "+ mortgage);
        return response;
    }
}
