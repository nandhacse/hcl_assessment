package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.impl;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.DepositOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.InvalidAccountException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.TransferOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Account;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.AccountStatus;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.AccountType;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void deposit_success() throws DepositOperationException {
        Account account = new Account();
        account.setAccountId(1);
        account.setAccountNumber("12345");
        account.setAccountType(AccountType.SAVINGS);
        account.setBalance(BigDecimal.valueOf(1000.12));

        long amount = 100;
        Account newAccount = new Account();
        newAccount.setBalance(BigDecimal.valueOf(1000.12 + amount));
        when(accountRepository.findByAccountNumber(eq("12345"))).thenReturn(account);
        when(accountRepository.saveAndFlush(any(Account.class))).thenReturn(newAccount);
        Long accountNumber = Long.parseLong(account.getAccountNumber());
        assertDoesNotThrow(()-> transactionService.deposit(accountNumber, amount, "deposited 100"));
        assertEquals(1100.12, newAccount.getBalance().doubleValue());
    }

    @Test
    void deposit_invalidAccountId() throws DepositOperationException {
        Account account = new Account();
        account.setAccountId(2);
        account.setAccountNumber("6789");
        account.setAccountType(AccountType.SAVINGS);
        account.setBalance(BigDecimal.valueOf(1000.12));

        when(accountRepository.findByAccountNumber(eq("6789"))).thenReturn(null);
        Long accountNumber = Long.parseLong(account.getAccountNumber());
        assertThrows(DepositOperationException.class, ()-> transactionService.deposit(accountNumber, 100, "deposited 100"));
    }

    @Test
    void deposit_negativeAmount() throws DepositOperationException {
        Account account = new Account();
        account.setAccountId(2);
        account.setAccountNumber("6789");
        account.setAccountType(AccountType.SAVINGS);
        account.setBalance(BigDecimal.valueOf(-1));

        when(accountRepository.findByAccountNumber(eq("6789"))).thenReturn(null);
        Long accountNumber = Long.parseLong(account.getAccountNumber());
        assertThrows(DepositOperationException.class, ()-> transactionService.deposit(accountNumber, 100, "deposited 100"));
    }

    @Test
    void deposit_inactiveAccount() throws DepositOperationException {
        Account account = new Account();
        account.setAccountId(2);
        account.setAccountNumber("6789");
        account.setStatus(AccountStatus.INACTIVE);
        account.setAccountType(AccountType.SAVINGS);
        account.setBalance(BigDecimal.valueOf(1000.12));

        when(accountRepository.findByAccountNumber(eq("6789"))).thenReturn(null);
        Long accountNumber = Long.parseLong(account.getAccountNumber());
        assertThrows(DepositOperationException.class, ()-> transactionService.deposit(accountNumber, 100, "deposited 100"));
    }

    @Test
    void deposit_sqlException() throws DepositOperationException {
        Account account = new Account();
        account.setAccountId(2);
        account.setAccountNumber("6789");
        account.setAccountType(AccountType.SAVINGS);
        account.setBalance(BigDecimal.valueOf(1000.12));

        when(accountRepository.findByAccountNumber(eq("6789"))).thenReturn(account);
        when(accountRepository.saveAndFlush(any(Account.class))).thenThrow(new RuntimeException());
        Long accountNumber = Long.parseLong(account.getAccountNumber());
        assertThrows(DepositOperationException.class, ()-> transactionService.deposit(accountNumber, 100, "deposited 100"));
    }

    @Test
    void transfer_success() throws DepositOperationException {
        Account fromAccount = new Account();
        fromAccount.setAccountId(1);
        fromAccount.setAccountNumber("12345");
        fromAccount.setStatus(AccountStatus.ACTIVE);
        fromAccount.setAccountType(AccountType.SAVINGS);
        fromAccount.setBalance(BigDecimal.valueOf(1000.12));

        Account toAccount = new Account();
        toAccount.setAccountId(2);
        toAccount.setAccountNumber("6789");
        toAccount.setStatus(AccountStatus.ACTIVE);
        toAccount.setAccountType(AccountType.MORTGAGE);
        toAccount.setBalance(BigDecimal.valueOf(2000.12));



        long amount = 100;
        Account newFromAccount = new Account();
        newFromAccount.setBalance(BigDecimal.valueOf(1000.12 - amount));
        Account newToAccount = new Account();
        newToAccount.setBalance(BigDecimal.valueOf(2000.12 + amount));

        when(accountRepository.findByAccountNumber(eq("12345"))).thenReturn(fromAccount);
        when(accountRepository.save(eq(fromAccount))).thenReturn(newFromAccount);
        when(accountRepository.findByAccountNumber(eq("6789"))).thenReturn(toAccount);
        when(accountRepository.save(eq(toAccount))).thenReturn(newToAccount);
        Long fromAccountNumber = Long.parseLong(fromAccount.getAccountNumber());
        Long toAccountNumber = Long.parseLong(toAccount.getAccountNumber());
        assertDoesNotThrow(()-> transactionService.transfer(fromAccountNumber, toAccountNumber,amount, "deposited 100"));
        assertEquals(2000.12 + amount, newToAccount.getBalance().doubleValue());
        assertEquals(1000.12 - amount, newFromAccount.getBalance().doubleValue());
    }

    @Test
    void transfer_notMortgageAccount() throws DepositOperationException {
        Account fromAccount = new Account();
        fromAccount.setAccountId(1);
        fromAccount.setAccountNumber("12345");
        fromAccount.setStatus(AccountStatus.ACTIVE);
        fromAccount.setAccountType(AccountType.MORTGAGE);
        fromAccount.setBalance(BigDecimal.valueOf(1000.12));

        Account toAccount = new Account();
        toAccount.setAccountId(2);
        toAccount.setAccountNumber("6789");
        toAccount.setStatus(AccountStatus.ACTIVE);
        toAccount.setAccountType(AccountType.SAVINGS);
        toAccount.setBalance(BigDecimal.valueOf(1000.12));


        when(accountRepository.findByAccountNumber(eq("12345"))).thenReturn(fromAccount);
        Long fromAccountNumber = Long.parseLong(fromAccount.getAccountNumber());
        Long toAccountNumber = Long.parseLong(fromAccount.getAccountNumber());
        assertThrows(TransferOperationException.class, ()-> transactionService.transfer(fromAccountNumber, toAccountNumber,100, "deposited 100"));
    }

    @Test
    void transfer_DBInconsistency() throws DepositOperationException {
        Account fromAccount = new Account();
        fromAccount.setAccountId(1);
        fromAccount.setAccountNumber("12345");
        fromAccount.setStatus(AccountStatus.ACTIVE);
        fromAccount.setAccountType(AccountType.SAVINGS);
        fromAccount.setBalance(BigDecimal.valueOf(1000.12));

        Account toAccount = new Account();
        toAccount.setAccountId(2);
        toAccount.setAccountNumber("6789");
        toAccount.setStatus(AccountStatus.ACTIVE);
        toAccount.setAccountType(AccountType.MORTGAGE);
        toAccount.setBalance(BigDecimal.valueOf(1100.12));

        when(accountRepository.findByAccountNumber(eq("12345"))).thenReturn(fromAccount);
        when(accountRepository.findByAccountNumber(eq("6789"))).thenReturn(toAccount);
        Long fromAccountNumber = Long.parseLong(fromAccount.getAccountNumber());
        Long toAccountNumber = Long.parseLong(toAccount.getAccountNumber());
        when(accountRepository.save(eq(fromAccount))).thenReturn(fromAccount);
        when(accountRepository.save(eq(toAccount))).thenThrow(new RuntimeException());
        assertThrows(TransferOperationException.class, ()-> transactionService.transfer(fromAccountNumber, toAccountNumber,100, "deposited 100"));
    }
}