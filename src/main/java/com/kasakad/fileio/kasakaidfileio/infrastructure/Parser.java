package com.kasakad.fileio.kasakaidfileio.infrastructure;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Parser {

//    public static final String LOCAL_DATE_FORMAT = "yyyy-MM-dd";
//
//    public static final String LOCAL_DATETIME_FORMAT = "yyyy-MM-ddTHH:mm:ss";

    public DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    public String deserialize(LocalDateTime localDateTime) {
        return dateTimeFormatter().format(localDateTime);
    }

    public DateTimeFormatter dateTimeFormatter() {
//        return DateTimeFormatter.ofPattern(LOCAL_DATETIME_FORMAT);
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }
}
