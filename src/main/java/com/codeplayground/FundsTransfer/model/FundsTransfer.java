package com.codeplayground.FundsTransfer.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FundsTransfer {

    private String accFromId;
 
    private String accToId;

    @NotNull(message = "Amount required")
    @Min(value=10, message="Minimum transfer amount is $10")
    private Float amount;

    private String comments;



    public String getAccFromId() {return this.accFromId;}
    public void setAccFromId(String accFromId) {this.accFromId = accFromId;}

    public String getAccToId() {return this.accToId;}
    public void setAccToId(String accToId) {this.accToId = accToId;}
    
    public Float getAmount() {return this.amount;}
    public void setAmount(Float amount) {this.amount = amount;}

    public String getComments() {return this.comments;}
    public void setComments(String comments) {this.comments = comments;}


    // For logging purposes
    @Override
    public String toString() {
        return "{" +
            " accFromId='" + getAccFromId() + "'" +
            ", accToId='" + getAccToId() + "'" +
            ", amount='" + getAmount() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }

}
