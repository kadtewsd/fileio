package com.kasakad.fileio.kasakaidfileio.infrastructure;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Parser {

    public static final String LOCAL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    public String deserialize(LocalDateTime localDateTime) {
        return dateTimeFormatter().format(localDateTime);
    }

    /**
     * 日にちと時間の間にスペースを入れた方が入力しやすい希ガスので、ISO 標準を採用しません
     * @return
     */
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern(LOCAL_DATETIME_FORMAT);
//        return DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }
}
