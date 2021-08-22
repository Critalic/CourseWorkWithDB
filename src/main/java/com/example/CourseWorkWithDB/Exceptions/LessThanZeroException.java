package com.example.CourseWorkWithDB.Exceptions;

public class LessThanZeroException extends Exception {
    public LessThanZeroException() {
    }
    public LessThanZeroException(String message) {
        super(message);
    }
}
