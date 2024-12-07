package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String accountNumber;
    private String accountType;
    private LocalDate transactionDate;
    private double balance;
    private String customerId;
}
