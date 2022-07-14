package com.example.CourseWorkWithDB.Services;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Consumer;

public class Utils {
    private Utils() {
    }

    public static String generateID(String name, String owner, String info) {
        LocalDateTime now = LocalDateTime.now();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(owner).append(info).append(now);
        return stringBuilder.toString();
    }
}
