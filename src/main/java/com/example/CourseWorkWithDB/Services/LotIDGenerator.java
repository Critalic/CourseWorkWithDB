package com.example.CourseWorkWithDB.Services;

import java.time.LocalDateTime;

public class LotIDGenerator {
    private LotIDGenerator(){}

    public static String generateID (String name, String owner, String info) {
        LocalDateTime now = LocalDateTime.now();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(owner).append(info).append(now);
        return stringBuilder.toString();
    }
}
