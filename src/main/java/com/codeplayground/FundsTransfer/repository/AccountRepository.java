package com.codeplayground.FundsTransfer.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.codeplayground.FundsTransfer.model.Account;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@Repository
public class AccountRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllSQL = "select * from accounts";
    private final String findByIDSQL = "select * from accounts where account_id=?";
    private final String withdrawSQL = "update accounts set balance = balance - ? where account_id = ?";
    private final String depositSQL = "update accounts set balance = balance + ? where account_id = ?";


    // #1 Find all Accounts
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>(); 
        accounts = jdbcTemplate.query(findAllSQL, BeanPropertyRowMapper.newInstance(Account.class));  
        return accounts;
    }

    // #2 Find account by ID
    public Account findByID(String id) {
        Account account = new Account();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(findByIDSQL, id);
        while (rs.next()) {
            account.setAccountId(id);
            account.setName(rs.getString("name"));
            account.setBalance(rs.getFloat("balance"));
        }
        return account;
    }

    // #3 Withdrawal
    public Boolean withdrawAmount(String accountId, Float withdrawnAmount) {
        Boolean bWithdrawn = false; // returns boolean true if successfully withdrawn
        int iUpdated = 0;
        iUpdated = jdbcTemplate.update(withdrawSQL, withdrawnAmount, accountId);
        if (iUpdated > 0) {
            bWithdrawn = true;
        }
        return bWithdrawn;
    }

    // #4 Deposit
    public Boolean depositAmount(String accountId, Float depositAmount) {
        Boolean bDeposited = false;
        int iDeposited = 0;
        iDeposited = jdbcTemplate.update(depositSQL, depositAmount, accountId);
        if (iDeposited > 0) {
            bDeposited = true;
        }
        return bDeposited;
    }
    
}
