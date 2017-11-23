package com.interview.exceptions;

import com.interview.exceptions.result.Results;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class BaseException extends RuntimeException {
    private static final Logger LOGGER = getLogger(BaseException.class);
    private final String message;
    private final Results results;

    BaseException(String message, Results results) {
        this.message = message;
        this.results = results;
        LOGGER.error("Exception message: {}. Results: {}", message, results.toString());
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Results getResults() {
        return results;
    }
}