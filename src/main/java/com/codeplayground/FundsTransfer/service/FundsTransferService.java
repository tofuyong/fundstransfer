package com.codeplayground.FundsTransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.codeplayground.FundsTransfer.model.Account;
import com.codeplayground.FundsTransfer.model.FundsTransfer;
import com.codeplayground.FundsTransfer.model.Transaction;
import com.codeplayground.FundsTransfer.repository.AccountRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class FundsTransferService {
    private static final Logger logger = LoggerFactory.getLogger(FundsTransferService.class);

    @Autowired
    AccountRepository accRepo;

    // #1 Find all Accounts
    public List<Account> findAll() {
        return accRepo.findAll();
    }

    // #2 Find account by ID
    public Account findByID(String id) {
        return accRepo.findByID(id);
    }
    
    // #3 Withdrawal
    public Boolean withdrawAmount(String accountId, Float withdrawnAmount) {
        return accRepo.withdrawAmount(accountId, withdrawnAmount);
    }

    // #4 Deposit
    public Boolean depositAmount(String accountId, Float depositAmount) {
        return accRepo.depositAmount(accountId, depositAmount);
    }


    public Boolean performValidation (FundsTransfer fundstrsf, Float amount) {
        Boolean bProceed = false;

        // C2. Check that both From & To Accounts are not the same
        if (fundstrsf.getAccFromId() != fundstrsf.getAccToId()) {
            bProceed = true;
        }

        // C5. Check that AccFrom has sufficient funds to perform the transfer (AccFrom balance > transfer amount)
        Account accFrom = accRepo.findByID(fundstrsf.getAccFromId());
        Float accFromBalance = accFrom.getBalance();
        if (accFromBalance >= amount) {
            bProceed = true;
        } else {
            bProceed = false;
        }
        return bProceed;
    }


    // @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Boolean performTransaction (FundsTransfer fundstrsf, Float amount)  {
        Boolean bWithdrawn = false;
        Boolean bDeposited = false;
        Boolean bTransacted = false;

        // Perform withdrawal
        bWithdrawn = accRepo.withdrawAmount(fundstrsf.getAccFromId(), amount);

        // Perform Deposit
        bDeposited = accRepo.depositAmount(fundstrsf.getAccToId(), amount);

        // Check Transactions successful
        if (bWithdrawn && bDeposited) {
            bTransacted = true;
        }
        return bTransacted;
    }

}
