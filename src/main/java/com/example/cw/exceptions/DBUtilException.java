package com.example.cw.exceptions;

public class DBUtilException extends RuntimeException {
    private String message;
    private Throwable cause;

    public DBUtilException() {
    }

    public DBUtilException(String message) {
        this.message = message;
    }

    public DBUtilException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }
}
