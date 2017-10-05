package com.velotrade.sdk.entity;

public class Auction {

    private Debtor debtor;

    //information from debtorContact
    private String debtorName;
    private String debtorEmail;
    private String debtorPhone;

    //information from invoice
    private String number;
    private String issueDate;
    private String currency;
    private float amount;
    private float expectedAmount;
    private float paymentTerms;
    private String dueDate;
    private String expectedPaymentDate;
    private String description;

    private Attachment invoice;
    private Attachment transportationDocument;
    private Attachment purchaseOrder;

    public Auction(DebtorContact debtorContact, Invoice invoice, Attachment invoiceDocument, Attachment transportationDocument, Attachment purchaseOrder) {
        this.debtor = debtorContact.getDebtor();

        this.debtorName = debtorContact.getName();
        this.debtorEmail = debtorContact.getEmail();
        this.debtorPhone = debtorContact.getPhone();

        this.number = invoice.getNumber();
        this.issueDate = invoice.getIssueDate();
        this.currency = invoice.getCurrency();
        this.amount = invoice.getAmount();
        this.expectedAmount = invoice.getExpectedAmount();
        this.paymentTerms = invoice.getPaymentTerms();
        this.dueDate = invoice.getDueDate();
        this.expectedPaymentDate = invoice.getExpectedPaymentDate();
        this.description = invoice.getDescription();

        this.invoice = invoiceDocument;
        this.transportationDocument = transportationDocument;
        this.purchaseOrder = purchaseOrder;
    }

    public Debtor getDebtor() {
        return debtor;
    }

    public void setDebtor(Debtor debtor) {
        this.debtor = debtor;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public String getDebtorEmail() {
        return debtorEmail;
    }

    public void setDebtorEmail(String debtorEmail) {
        this.debtorEmail = debtorEmail;
    }

    public String getDebtorPhone() {
        return debtorPhone;
    }

    public void setDebtorPhone(String debtorPhone) {
        this.debtorPhone = debtorPhone;
    }

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

    public Attachment getInvoice() {
        return invoice;
    }

    public void setInvoice(Attachment invoice) {
        this.invoice = invoice;
    }

    public Attachment getTransportationDocument() {
        return transportationDocument;
    }

    public void setTransportationDocument(Attachment transportationDocument) {
        this.transportationDocument = transportationDocument;
    }

    public Attachment getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(Attachment purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
