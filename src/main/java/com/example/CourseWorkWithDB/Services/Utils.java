package com.example.CourseWorkWithDB.Services;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;

public class Utils {
    private Utils(){}

    public static String generateID (String name, String owner, String info) {
        LocalDateTime now = LocalDateTime.now();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(owner).append(info).append(now);
        return stringBuilder.toString();
    }

    public static Object getValue (Field field, Object identifier) {
        try {
            return field.get(identifier);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
