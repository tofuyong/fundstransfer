package com.codeplayground.FundsTransfer.model;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    private String transactionId;
    private LocalDate transferDate;
    private String accFromId; 
    private String accToId;
    private String accFromName; 
    private String accToName;
    private Float amount;


    public String getTransactionId() {return this.transactionId;}
    public void setTransactionId(String transactionId) {this.transactionId = transactionId;}

    public LocalDate getTransferDate() {return this.transferDate;}
    public void setTransferDate(LocalDate transferDate) {this.transferDate = transferDate;}
   
    public String getAccFromId() {return this.accFromId;}
    public void setAccFromId(String accFromId) {this.accFromId = accFromId;}

    public String getAccToId() {return this.accToId;}
    public void setAccToId(String accToId) {this.accToId = accToId;}

    public String getAccFromName() {return this.accFromName;}
    public void setAccFromName(String accFromName) {this.accFromName = accFromName;}

    public String getAccToName() {return this.accToName;}
    public void setAccToName(String accToName) {this.accToName = accToName;}

    public Float getAmount() {return this.amount;}
    public void setAmount(Float amount) {this.amount = amount;}

}
