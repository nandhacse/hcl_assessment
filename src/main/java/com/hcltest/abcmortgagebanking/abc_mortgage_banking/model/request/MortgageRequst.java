package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request;

/**
 * This is the request object to hold mortgage account number and password
 */
public class MortgageRequst {
    private String mortgageNumber;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMortgageNumber() {
        return mortgageNumber;
    }

    public void setMortgageNumber(String mortgageNumber) {
        this.mortgageNumber = mortgageNumber;
    }
}
