package com.example.CourseWorkWithDB.Exceptions;

public class DBError extends Exception {
    public DBError() {
    }
    public DBError(String message) {
        super(message);
    }
}
