package com.capgemini.interview.exceptions;

public class DatabaseErrorException extends RuntimeException {
    private static String INTERNAL_SERVER_ERROR_MSG_PREFIX =  "DATABASE_SERVER_ERROR:";

    public DatabaseErrorException() {
        super(INTERNAL_SERVER_ERROR_MSG_PREFIX);
    }

    public DatabaseErrorException(String message) {
        super(INTERNAL_SERVER_ERROR_MSG_PREFIX + message);
    }

    public DatabaseErrorException(String message, Throwable cause) {
        super(INTERNAL_SERVER_ERROR_MSG_PREFIX + message, cause);
    }

    protected DatabaseErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(INTERNAL_SERVER_ERROR_MSG_PREFIX + message, cause, enableSuppression, writableStackTrace);
    }
}
