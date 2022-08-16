package com.example.cw.exceptions;

public class DBException extends RuntimeException {
    private String message;
    private Throwable cause;

    public DBException() {
    }

    public DBException(String message) {
        this.message = message;
    }

    public DBException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }
}
