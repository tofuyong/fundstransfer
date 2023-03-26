package com.codeplayground.FundsTransfer.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Account {
    
    @NotNull(message = "Account ID is required")
    @Size(min=10, max=10, message= "Acc ID must be 10 characters long") 
    private String accountId;

    @NotNull(message = "Name is required")
    private String name;

    @Min(value=0, message="Balance cannot go below $0")
    private Float balance;


    public String getAccountId() {return this.accountId;}
    public void setAccountId(String accountId) {this.accountId = accountId;}

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public Float getBalance() {return this.balance;}
    public void setBalance(Float balance) {this.balance = balance;}

}
