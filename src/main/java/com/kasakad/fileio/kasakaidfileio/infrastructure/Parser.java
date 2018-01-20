package com.kasakad.fileio.kasakaidfileio.infrastructure;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class Parser {

    public static final String LOCAL_DATE_FORMAT = "yyyy-MM-dd";

    public static final String LOCAL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern(LOCAL_DATE_FORMAT);
    }

    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern(LOCAL_DATETIME_FORMAT);
    }
}
