package com.example.CourseWorkWithDB.Exceptions;

public class DataBaseException extends RuntimeException {
    private String message;
    private Throwable cause;

    public DataBaseException() {
    }

    public DataBaseException(String message) {
        this.message = message;
    }

    public DataBaseException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }
}
