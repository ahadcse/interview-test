package com.interview.exceptions;

import com.interview.exceptions.result.Results;

public class WithdrawalException extends RuntimeException {
    private final String message;
    private final Results results;

    public WithdrawalException (String message, Results results) {
        this.message = message;
        this.results = results;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Results getResults() {
        return results;
    }
}
