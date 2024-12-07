package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the primary key (account_id)
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;

    @Enumerated(EnumType.STRING) // Store enum as string in DB (SAVINGS, CURRENT, MORTGAGE)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency = "USD"; // Default is USD

    @Enumerated(EnumType.STRING) // Store enum as string (ACTIVE, INACTIVE, CLOSED)
    @Column(name = "status", nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE; // Default is ACTIVE

    @Column(name = "last_transaction_date")
    private LocalDateTime lastTransactionDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}