package com.capgemini.interview.model.response;

public class BankResponse {
    private int accountId;
    private String accountName;
    private double balance;
    private double amount;


    public BankResponse(int accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public BankResponse(int accountId, String accountName, double balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.balance = balance;
    }

    public BankResponse(int accountId, String accountName, double amount, double balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.amount = amount;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BankResponse{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", balance=" + balance +
                ", amount=" + amount +
                '}';
    }
}
