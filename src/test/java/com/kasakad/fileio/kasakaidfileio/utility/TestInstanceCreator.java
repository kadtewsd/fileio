package com.kasakad.fileio.kasakaidfileio.utility;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kasakad.fileio.kasakaidfileio.infrastructure.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TestInstanceCreator {

    @Autowired
    private Parser parser;

    public CsvMapper csvObjectMapper() {
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(parser.dateFormatter()));
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(parser.dateFormatter()));
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(parser.dateTimeFormatter()));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(parser.dateTimeFormatter()));

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(String.class, new StringNullDeserializer());
        simpleModule.addDeserializer(Boolean.class, new BooleanDeserializer());
        simpleModule.addDeserializer(boolean.class, new BooleanDeserializer());
        return (CsvMapper) new CsvMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .registerModule(timeModule)
                .registerModule(simpleModule);
    }
}
