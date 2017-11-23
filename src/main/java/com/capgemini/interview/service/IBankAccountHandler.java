package com.capgemini.interview.service;

import com.capgemini.interview.exceptions.BankAccountException;
import com.capgemini.interview.model.response.BankResponse;

public interface IBankAccountHandler
{
    /**
     * Create new account.
     *
     * @param accountName
     *            Name of the account
     *
     * @return Bank account id for created account
     * @throws BankAccountException if exception occurs
     */
    BankResponse createBankAccount(String accountName);

    /**
     * Withdrawal from account.
     *
     * @param bankAccountId
     *            Account id
     * @param amount
     *            The amount to withdrawal
     *
     * @return Account balance after withdrawal
     * @throws BankAccountException
     *             if exception occurs
     */
    BankResponse withdrawal(int bankAccountId, float amount);

    /**
     * Deposit to account.
     *
     * @param bankAccountId
     *            Account id
     * @param amount
     *            Amount to deposit
     *
     * @return Account balance after deposit
     * @throws BankAccountException if exception occurs
     */
    BankResponse deposit(int bankAccountId, float amount);

    /**
     * Get balance of an account.
     *
     * @param bankAccountId
     *            Account id
     *
     * @return Get balance of an account
     */
    float getBalance(int bankAccountId);

}
