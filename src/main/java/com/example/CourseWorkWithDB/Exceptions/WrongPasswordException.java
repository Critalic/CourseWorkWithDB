package com.example.CourseWorkWithDB.Exceptions;

public class WrongPasswordException extends Exception {
    public WrongPasswordException() {
    }
    public WrongPasswordException(String message) {
        super(message);
    }
}
