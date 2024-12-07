package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model;

import jakarta.persistence.*;

/**
 * This is the Entity class which is used to store the mortgage into db
 */
@Entity
@Table(name = "mortgage")
public class Mortgage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mortgage_id")
    private Long mortgageId;

    @Column(name="mortgage_type", length=50, nullable=false, unique=false)
    private String mortgageType;

    @Column(name="mortgage_account_number", length=50, nullable=false, unique=false)
    private double mortgageAccountNumber;

    @Column(name="customer_id", length=250, nullable=false, unique=false)
    private String customerId;

    @Column(name="password", length=20, nullable=false, unique=false)
    private String password;


    public String getMortgageType() {
        return mortgageType;
    }

    public void setMortgageType(String mortgageType) {
        this.mortgageType = mortgageType;
    }

    public Long getMortgageId() {
        return mortgageId;
    }

    public void setMortgageId(Long mortgageId) {
        this.mortgageId = mortgageId;
    }

    public double getMortgageAccountNumber() {
        return mortgageAccountNumber;
    }

    public void setMortgageAccountNumber(double mortgageAccountNumber) {
        this.mortgageAccountNumber = mortgageAccountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
