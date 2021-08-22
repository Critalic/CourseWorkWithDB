package com.example.CourseWorkWithDB.Validators;

import com.example.CourseWorkWithDB.Exceptions.InvalidEmailException;

public class EmailValidator {
    public static void validate(String email) throws InvalidEmailException {
        if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) throw new InvalidEmailException("Not a valid e-mail");
    }
}
