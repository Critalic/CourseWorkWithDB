package com.example.CourseWorkWithDB.Validators;

public class EmptyValidator {
        public static String checkIfEmpty(String stringToValidate, String stringName)
                throws IllegalArgumentException, NullPointerException {
            if (stringToValidate == null) {
                throw new NullPointerException(stringName+" is required!");
            }
            String resultString = stringToValidate.trim();
            if (resultString.isEmpty()) {
                throw new IllegalArgumentException(stringName+" is required!");
            }
            return resultString;
        }
}
