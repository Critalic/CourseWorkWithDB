package com.example.CourseWorkWithDB.Validators;

public class NumberValidator {
    public static void moreThanZero(double value, String valueName) throws LessThanZeroException {
        if(value<0) throw new LessThanZeroException(valueName + " should be more than or equal to zero");
    }

    public static void lessThanGivenNumber(double value, double number) throws LessThanGivenException {
        if(value<=number) throw new LessThanGivenException("The proposition should be bigger than the current price");
    }
}
