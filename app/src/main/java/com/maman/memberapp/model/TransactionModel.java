package com.maman.memberapp.model;

public class TransactionModel {
    String titleTransaction, dateTransaction, amountTransaction;

    public String getTitleTransaction() {
        return titleTransaction;
    }

    public void setTitleTransaction(String titleTransaction) {
        this.titleTransaction = titleTransaction;
    }

    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(String amountTransaction) {
        this.amountTransaction = amountTransaction;
    }

    public TransactionModel(String titleTransaction, String dateTransaction, String amountTransaction) {
        this.titleTransaction = titleTransaction;
        this.dateTransaction = dateTransaction;
        this.amountTransaction = amountTransaction;
    }
}
