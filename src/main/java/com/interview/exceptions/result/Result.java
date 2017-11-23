package com.interview.exceptions.result;

import com.fasterxml.jackson.annotation.*;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Result {
    OK (200, "Successful"),
    FAILED (2000, "Unsuccessful"),
    DB_ERROR(2001, "Internal Server Error"),
    EMPTY_ACCOUNT_NAME(2002, "Empty account name"),
    ACCOUNT_CREATION_ERROR(2003, "Account creation error"),
    ACCOUNT_NOT_AVAILABLE(2004, "Account does not exist"),
    NEGATIVE_AMOUNT_ERROR(2005, "Negative Amount Error"),
    WITHDRAW_ERROR(2006, "Amount withdraw error"),
    DEPOSIT_ERROR(2007, "Amount deposit error");


    private int code;
    private String message;

    Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @JsonIgnore
    public boolean isOK() {
        return this == OK;
    }
}