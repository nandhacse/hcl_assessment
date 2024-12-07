package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.impl;

import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.DepositOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.InvalidAccountException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.TransferOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.WithdrawOperationException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.AccountTransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("accountTransactionalService")
@Slf4j
public class AccountTransactionalServiceImpl implements AccountTransactionalService {

    @Override
    public void deposit(Long accountId, double amount, String remarks) throws DepositOperationException {
        //Not needed
    }

    @Override
    public void withdraw(Long accountId, double amount, String remarks) throws WithdrawOperationException {
        //Not needed
    }

    @Override
    public void transfer(Long fromAccountId, Long toAccountId, double amount, String remarks) throws InvalidAccountException, TransferOperationException {
        log.info("Start of transfer, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}", fromAccountId, toAccountId, amount, remarks);
        try {
            Account fromAccount = accountStorageDB.getAccount(fromAccountId);
            Account toAccount = accountStorageDB.getAccount(toAccountId);
            boolean subtractStatus = fromAccount.subtractBalance(amount, remarks);
            log.info("transfer operation, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}, subtractStatus: {}", fromAccountId, toAccountId, amount, remarks, subtractStatus);
            if (subtractStatus) {
                boolean addStatus = toAccount.addBalance(amount, remarks);
                log.info("End of transfer, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}, addStatus: {}", fromAccountId, toAccountId, amount, remarks, addStatus);
                if (!addStatus) {
                    log.info("revertWithdrawalOperation, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}, addStatus: {}", fromAccountId, toAccountId, amount, remarks, addStatus);
                    revertWithdrawalOperation(fromAccount, amount, remarks);
                    throw new TransferOperationException();
                }
            } else {
                throw new TransferOperationException();
            }
        } catch (TransferOperationException | RuntimeException | InvalidAccountException ex) {
            log.error("Error in transfer, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}", fromAccountId, toAccountId, amount, remarks, ex);
            throw new TransferOperationException(ex.getMessage());
        } catch (Exception ex) {
            log.error("Error in transfer, fromAccountId: {}, toAccountId: {}, amount: {}, remarks: {}", fromAccountId, toAccountId, amount, remarks, ex);
            throw new TransferOperationException();
        }
    }
}
