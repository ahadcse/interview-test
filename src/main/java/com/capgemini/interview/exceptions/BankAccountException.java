package com.capgemini.interview.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final public class BankAccountException extends Exception
{
    private static final long serialVersionUID = -314439670131687936L;
    private static Logger LOGGER = LoggerFactory.getLogger(BankAccountException.class);

    /**
     * Constructor class
     *
     * @param reason
     *            Reason of the exception
     */
    public BankAccountException(String reason)
    {
        super(reason);
        LOGGER.info("Error: " + reason);
    }
}