package com.codeplayground.FundsTransfer.service;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.grammars.hql.HqlParser.BooleanExpressionPredicateContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.codeplayground.FundsTransfer.model.Account;
import com.codeplayground.FundsTransfer.model.Transaction;
import com.codeplayground.FundsTransfer.repository.AccountRepository;

@Service
public class LogAuditService {

    @Autowired
    AccountRepository accRepo;

    // @Autowired
    // RedisTemplate<String, String> redisTemplate;
    
    public Transaction createTransaction (String accFromId, String accToId, Float amount) {
        String transactionId = UUID.randomUUID().toString().substring(0,8);  
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setTransferDate(LocalDate.now());
        transaction.setAccFromId(accFromId);
        transaction.setAccToId(accToId);
        transaction.setAmount(amount);
        
        Account accountFrom = accRepo.findByID(accFromId);
        String nameFrom = accountFrom.getName();
        transaction.setAccFromName(nameFrom);

        Account accountTo = accRepo.findByID(accToId);
        String nameTo = accountTo.getName();
        transaction.setAccToName(nameTo);

        return transaction;
    }

        // Save transfer details into Redis Database
        // public void logToRedis (Transaction transaction) { 
        //     redisTemplate.opsForHash().put("transaction", "transactionId", transaction.getTransactionId()); //name of map, key, value
        //     redisTemplate.opsForHash().put("transaction", "date", transaction.getTransferDate());
        //     redisTemplate.opsForHash().put("transaction", "from_account", transaction.getAccFromId());
        //     redisTemplate.opsForHash().put("transaction", "to_account", transaction.getAccToId());
        //     redisTemplate.opsForHash().put("transaction", "amount", transaction.getAmount());
        // }

}
