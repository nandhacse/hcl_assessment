package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.impl;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.controller.AccountController;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.DepositOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.InvalidAccountException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.TransferOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.WithdrawOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.*;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.AccountRepository;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.TransactionRepository;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void deposit(Long accountId, double amount, String remarks) throws InvalidAccountException, DepositOperationException {
        log.info("Start of deposit, accountId: {}, amount: {}, remarks: {}", accountId, amount, remarks);
        try {
            Account account = doValidationAndReturnAccount(accountId, amount);
            BigDecimal newBal = account.getBalance().add(BigDecimal.valueOf(amount));
            account.setBalance(newBal);
            saveBalanceToAccount(accountId, amount, remarks, account);
            addTransaction(account.getAccountNumber(), amount, TransactionType.DEPOSIT, remarks);
        } catch (RuntimeException | DepositOperationException ex) {
            log.error("Error in deposit, accountId: {}, amount: {}, remarks: {}", accountId, amount, remarks, ex);
            throw new DepositOperationException(ex.getMessage());
        } catch (Exception ex) {
            log.error("Error in deposit, accountId: {}, amount: {}, remarks: {}", accountId, amount, remarks, ex);
            throw new DepositOperationException();
        }
    }

    private Account doValidationAndReturnAccount(Long accountId, double amount) throws InvalidAccountException {
        if(amount < 0) throw new IllegalArgumentException();
        Account account = accountRepository.findByAccountNumber(accountId.toString());
        if (Objects.isNull(account) || !account.getStatus().equals(AccountStatus.ACTIVE)) throw new InvalidAccountException("Account not active");
        return account;
    }

    @Transactional
    public void saveBalanceToAccount(Long accountId, double amount, String remarks, Account account) throws DepositOperationException {
        Account newAccount = accountRepository.saveAndFlush(account);
        log.info("End of deposit, accountId: {}, amount: {}, remarks: {}", accountId, amount, remarks);
        if (Objects.isNull(newAccount)) {
            throw new DepositOperationException();
        }
    }

    @Override
    public void withdraw(Long accountId, double amount, String remarks) throws InvalidAccountException, WithdrawOperationException {

    }

    @Override
    public void transfer(Long fromAccountId, Long toAccountId, double amount, String remarks) throws InvalidAccountException, TransferOperationException {
        log.info("Start of transfer, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}", fromAccountId, toAccountId, amount, remarks);
        try {
            Account fromAccount = doValidationAndReturnAccount(fromAccountId, amount);
            Account toAccount = doValidationAndReturnAccount(toAccountId, amount);
            if(AccountType.MORTGAGE.equals(fromAccount.getAccountType())) throw new TransferOperationException("You cannot transfer from mortgage account.");
            if(!AccountType.MORTGAGE.equals(toAccount.getAccountType())) throw new TransferOperationException("To Account should be mortgage account.");

            log.info("transfer operation started, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}", fromAccountId, toAccountId, amount, remarks);
            transferAccount(fromAccount, toAccount, amount);
            log.info("transfer operation completed, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}", fromAccountId, toAccountId, amount, remarks);
            addTransaction(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), amount, TransactionType.DEPOSIT, remarks);
        } catch (TransferOperationException | RuntimeException | InvalidAccountException ex) {
            log.error("Error in transfer, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}", fromAccountId, toAccountId, amount, remarks, ex);
            throw new TransferOperationException(ex.getMessage());
        } catch (Exception ex) {
            log.error("Error in transfer, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}", fromAccountId, toAccountId, amount, remarks, ex);
            throw new TransferOperationException();
        }
    }

    @Override
    public List<Transaction> getTransaction(String accountId) {
        return transactionRepository.findBySenderAccountId(Long.parseLong(accountId));
    }

    @Transactional
    public void transferAccount(Account fromAccount, Account toAccount, double amount) throws TransferOperationException {
        BigDecimal fromBalance = fromAccount.getBalance();
        if(fromBalance.compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new TransferOperationException("Insufficient balance");
        }
        BigDecimal newToBalance = toAccount.getBalance().add(BigDecimal.valueOf(amount));
        BigDecimal newFromBalance = fromBalance.subtract(BigDecimal.valueOf(amount));
        fromAccount.setBalance(newFromBalance);
        toAccount.setBalance(newToBalance);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        accountRepository.flush();
    }

    public void addTransaction(String accountId, double amount, TransactionType transactionType, String remarks) {
        Transaction transaction = new Transaction();
        transaction.setReceiverAccountId(Long.parseLong(accountId));
        transaction.setAmount(BigDecimal.valueOf(amount));
        transaction.setTransactionType(transactionType);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setDescription(remarks);
        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionRepository.saveAndFlush(transaction);
    }

    public void addTransaction(String fromAccountId, String toAccountId, double amount, TransactionType transactionType, String remarks) {
        Transaction transaction = new Transaction();
        transaction.setSenderAccountId(Long.parseLong(fromAccountId));
        transaction.setReceiverAccountId(Long.parseLong(toAccountId));
        transaction.setAmount(BigDecimal.valueOf(amount));
        transaction.setTransactionType(transactionType);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setDescription(remarks);
        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionRepository.saveAndFlush(transaction);
    }
}
