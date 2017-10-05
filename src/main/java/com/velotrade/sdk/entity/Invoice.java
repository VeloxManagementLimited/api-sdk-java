package com.velotrade.sdk.entity;

public class Invoice {

    private String number;
    private String issueDate;
    private String currency;
    private float amount;
    private float expectedAmount;
    private float paymentTerms;
    private String dueDate;
    private String expectedPaymentDate;
    private String description;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getExpectedPaymentDate() {
        return expectedPaymentDate;
    }

    public void setExpectedPaymentDate(String expectedPaymentDate) {
        this.expectedPaymentDate = expectedPaymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
