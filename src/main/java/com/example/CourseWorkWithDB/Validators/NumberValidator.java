package com.example.CourseWorkWithDB.Validators;

import com.example.CourseWorkWithDB.Exceptions.LessThanGivenException;
import com.example.CourseWorkWithDB.Exceptions.LessThanZeroException;

public class NumberValidator {
    public static long moreThanZero(long value, String valueName) throws LessThanZeroException {
        if(value<0) throw new LessThanZeroException(valueName + " should be more than or equal to zero");
        return value;
    }

    public static long lessThanGivenNumber(long value, long number) throws LessThanGivenException {
        if(value<=number) throw new LessThanGivenException("The proposition should be bigger than the current price");
        return value;
    }
}
