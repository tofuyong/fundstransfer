package com.codeplayground.FundsTransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplayground.FundsTransfer.model.Account;
import com.codeplayground.FundsTransfer.repository.AccountRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class FundsTransferService {
    private static final Logger logger = LoggerFactory.getLogger(FundsTransferService.class);

    @Autowired
    AccountRepository accRepo;

    public Boolean performTransfer (Account accFrom, Account accTo, Float amount) {
        
        // 1. Check that both From & To Accounts exist

        // 3. AccFrom should not be the same as AccTo

        // 6. Check that AccFrom has sufficient funds to perform the transfer (AccFrom balance > transfer amount)
        
    



        return null;
    }

}
