package com.hcltest.abcmortgagebanking.abc_mortgage_banking.controller;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.DepositOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.InvalidAccountException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.TransferOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Account;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.DepositRequest;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.request.TransferRequest;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.TransactionService;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.impl.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("transaction")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity depositAmount(@RequestBody DepositRequest depositRequest) {
        log.info("start Deposit operation");
        try {
            Long accountId = Long.parseLong(depositRequest.accountId());
            Double amount = Double.parseDouble(depositRequest.amount());
            transactionService.deposit(accountId, amount, depositRequest.remarks());
            log.info("Deposit operation completed successfully");
            return ResponseEntity.ok().build();
        } catch (NumberFormatException ex) {
            log.error("Error in parsing input : {}", depositRequest);
            return ResponseEntity.badRequest().build();
        } catch (DepositOperationException | InvalidAccountException ex) {
            log.error("Error in performing deposit operation : {}", depositRequest);
            return new ResponseEntity(HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity transferAmount(@RequestBody TransferRequest transferRequest) {
        log.info("start transfer operation");
        try {
            Long fromAccountId = Long.parseLong(transferRequest.fromAccountId());
            Long toAccountId = Long.parseLong(transferRequest.toAccountId());
            Double amount = Double.parseDouble(transferRequest.amount());
            transactionService.transfer(fromAccountId, toAccountId, amount, transferRequest.remarks());
            log.info("Transfer operation completed successfully");
            return ResponseEntity.ok().build();
        } catch (NumberFormatException ex) {
            log.error("Error in parsing input : {}", transferRequest);
            return ResponseEntity.badRequest().build();
        } catch (TransferOperationException | InvalidAccountException ex) {
            log.error("Error in performing deposit operation : {}", transferRequest);
            return new ResponseEntity(HttpStatusCode.valueOf(500));
        }
    }
}