package com.capgemini.interview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements IBankAccountEventListener interface
 */
public class BankAccountEventListenerImpl implements IBankAccountEventListener
{
    private static Logger LOGGER = LoggerFactory.getLogger(BankAccountEventListenerImpl.class);

    public void onNegativeAccountBalance(int bankAccountId, float balance)
    {
        LOGGER.info("Transaction: Account " + bankAccountId + "is at negative balance" + ", balance: $" + balance);
    }
}
