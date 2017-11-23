package com.interview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerHandlerImpl implements ICustomerHandler{

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerHandlerImpl.class);

    @Override
    public void Sold(String s) {
        LOGGER.info("Market's message: " + s);
    }

    @Override
    public void Choice(String s) {
        LOGGER.info("Market's message: " + s);
    }
}
