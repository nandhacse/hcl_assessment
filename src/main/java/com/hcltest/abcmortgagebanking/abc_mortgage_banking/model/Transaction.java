package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction") // Specifies the table name in the database
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating the primary key (transaction_id)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "sender_account_id", nullable = false)
    private Long senderAccountId;

    @Column(name = "receiver_account_id", nullable = false)
    private Long receiverAccountId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING) // Maps enum value as a string in the database
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "transaction_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING) // Maps enum value as a string in the database
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('PENDING', 'COMPLETED', 'FAILED') DEFAULT 'PENDING'")
    private TransactionStatus status;

    @Column(name = "description", length = 255)
    private String description;

    // Default constructor
    public Transaction() {}

    // Constructor with all fields
    public Transaction(Long senderAccountId, Long receiverAccountId, BigDecimal amount,
                       TransactionType transactionType, LocalDateTime transactionDate,
                       TransactionStatus status, String description) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.status = status;
        this.description = description;
    }
}
