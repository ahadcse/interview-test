package com.interview.service;

import com.interview.model.response.BankResponse;

public interface IBankAccountHandler
{
    /**
     * Create new account.
     *
     * @param accountName
     *            Name of the account
     *
     * @return Bank account id for created account
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
