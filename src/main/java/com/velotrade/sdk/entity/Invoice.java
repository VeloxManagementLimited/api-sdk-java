package com.velotrade.sdk.entity;

import java.util.Date;

public class Invoice {

    private int number;
    private Date issueDate;
    private float currency;
    private float amount;
    private float expectedAmount;
    private float paymentTerms;
    private Date dueDate;
    private Date expectedPaymentDate;
    private String description;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public float getCurrency() {
        return currency;
    }

    public void setCurrency(float currency) {
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(float expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public float getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(float paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getExpectedPaymentDate() {
        return expectedPaymentDate;
    }

    public void setExpectedPaymentDate(Date expectedPaymentDate) {
        this.expectedPaymentDate = expectedPaymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
