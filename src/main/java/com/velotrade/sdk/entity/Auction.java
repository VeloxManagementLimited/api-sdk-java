package com.velotrade.sdk.entity;

public class Auction {

    private Debtor debtor;
    private DebtorContact debtorContact;
    private Invoice invoice;

    private Attachment invoiceAttachment;
    private Attachment transportationDocument;
    private Attachment purchaseOrder;

    public Auction(Debtor debtor, DebtorContact debtorContact, Invoice invoice, Attachment invoiceAttachment, Attachment transportationDocument, Attachment purchaseOrder) {
        this.debtor = debtor;
        this.debtorContact = debtorContact;
        this.invoice = invoice;
        this.invoiceAttachment = invoiceAttachment;
        this.transportationDocument = transportationDocument;
        this.purchaseOrder = purchaseOrder;
    }

    public Debtor getDebtor() {
        return debtor;
    }

    public void setDebtor(Debtor debtor) {
        this.debtor = debtor;
    }

    public DebtorContact getDebtorContact() {
        return debtorContact;
    }

    public void setDebtorContact(DebtorContact debtorContact) {
        this.debtorContact = debtorContact;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Attachment getInvoiceAttachment() {
        return invoiceAttachment;
    }

    public void setInvoiceAttachment(Attachment invoiceAttachment) {
        this.invoiceAttachment = invoiceAttachment;
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
