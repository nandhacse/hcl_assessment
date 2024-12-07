package com.hcltest.abcmortgagebanking.abc_mortgage_banking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}