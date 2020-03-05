package com.interview;

public class QueueEmptyException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String message;

    public QueueEmptyException(String s) {
        this.message = s;
    }
}
