package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.ServiceException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Mortgage;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.LoginRequest;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.MortgageRequst;

public interface MortgageService {
    String save(Mortgage mortgage) throws ServiceException;
    String loginUsingMortgage(MortgageRequst MortgageRequst) throws ServiceException;
}
