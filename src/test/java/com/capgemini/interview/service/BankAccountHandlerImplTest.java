package com.capgemini.interview.service;

import java.util.List;

import com.capgemini.interview.exceptions.BankAccountException;
import com.capgemini.interview.model.bank.Account;
import com.capgemini.interview.model.bank.Transaction;
import com.capgemini.interview.model.response.BankResponse;
import com.capgemini.interview.store.DataStore;
import org.junit.Assert;
import org.junit.Test;


/**
 * Test class for bank operations
 */
public class BankAccountHandlerImplTest {
    private BankAccountHandlerImpl accountHolder = new BankAccountHandlerImpl();
    private DataStore dataStore = DataStore.getInstance();

    /**
     * Test for creating bank account
     */
    @Test
    public void testCreateBankAccount() throws BankAccountException {
        BankResponse bankResponse = accountHolder.createBankAccount("Account #1");
        Account retrievedAccount = dataStore.getAccount(bankResponse.getAccountId());
        Assert.assertEquals("Account #1", retrievedAccount.getAccountName());
    }

    /**
     * Test for withdrawing from bank account
     */
    @Test
    public void testWithdrawal() throws BankAccountException {
        BankResponse bankResponse = accountHolder.createBankAccount("Account #1");
        int accountId = bankResponse.getAccountId();
        accountHolder.withdrawal(accountId, 10.00f);
        sleep(2000);
        accountHolder.withdrawal(accountId, 20.00f);

        Assert.assertEquals(-30.00f, dataStore.getAccountBalance(accountId), 0.01);

        List<Transaction> transactions = dataStore.getLatestTransactions(accountId, 10);
        Assert.assertEquals(2, transactions.size());
        Assert.assertEquals(20.00f, transactions.get(0).getAmount(), 0.01);
        Assert.assertEquals(10.00f, transactions.get(1).getAmount(), 0.01);
    }

    /**
     * Test for deposit into bank account
     */
    @Test
    public void testDeposit() throws BankAccountException {
        BankResponse bankResponse = accountHolder.createBankAccount("Account #1");
        int accountId = bankResponse.getAccountId();

        accountHolder.deposit(accountId, 10.00f);
        sleep(2000);
        accountHolder.deposit(accountId, 20.00f);
        Assert.assertEquals(30.00f, dataStore.getAccountBalance(accountId), 0.01);

        List<Transaction> transactions = dataStore.getLatestTransactions(accountId, 10);
        Assert.assertEquals(2, transactions.size());
        Assert.assertEquals(20.00f, transactions.get(0).getAmount(), 0.01);
        Assert.assertEquals(10.00f, transactions.get(1).getAmount(), 0.01);
    }

    /**
     * Test for getting balance of the bank account
     */
    @Test
    public void testGetBalance() throws BankAccountException {
        BankResponse bankResponse = accountHolder.createBankAccount("Account #1");
        int accountId = bankResponse.getAccountId();

        accountHolder.deposit(accountId, 10.00f);
        accountHolder.withdrawal(accountId, 20.00f);
        accountHolder.deposit(accountId, 30.00f);
        accountHolder.withdrawal(accountId, 30.00f);
        Assert.assertEquals(-10.00f, accountHolder.getBalance(accountId), 0.01);
    }

    /**
     * Notification test while the balance is negative (Bullet point 6)
     */
    @Test
    public void testEventListener() throws BankAccountException {
        BankResponse bankResponse = accountHolder.createBankAccount("Account #1");
        int accountId = bankResponse.getAccountId();

        final StatusChecker statusChecker = new StatusChecker();
        statusChecker.status = false;
        accountHolder.addEventListener(new IBankAccountEventListener() {
            @Override
            public void onNegativeAccountBalance(int bankAccountId, float balance) {
                statusChecker.status = true;
            }
        });

        accountHolder.deposit(accountId, 10.00f);
        accountHolder.withdrawal(accountId, 20.00f);

        Assert.assertTrue(statusChecker.status);
    }

    private static class StatusChecker {
        public boolean status;
    }

    private void sleep(long sleepTimeMS) {
        try {
            Thread.sleep(sleepTimeMS);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}