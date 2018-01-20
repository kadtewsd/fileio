package com.kasakad.fileio.kasakaidfileio.utility;


import com.fasterxml.jackson.databind.DeserializationFeature;
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
public class InstanceCreator {

    @Autowired
    private Parser parser;

    /**
     * CsvMapper を Bean 化すると、ApplicationContext#getBean("objectMapper") で取得される型が、
     * 必ず CsvMapper になり、Json のシリアライズ/デシリアライズをしたいのに、CsvMapper でシリアライズ/デシリアライズされる。
     * そのため、Spring に影響を与えないような形で bean にします。
     *
     * @return
     */
    public CsvMapper csvObjectMapper() {
        return (CsvMapper) new CsvMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .registerModule(new JavaTimeModule()
                        .addSerializer(LocalDate.class, new LocalDateSerializer(parser.dateFormatter()))
                        .addDeserializer(LocalDate.class, new LocalDateDeserializer(parser.dateFormatter()))
                        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(parser.dateTimeFormatter()))
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(parser.dateTimeFormatter()))
                );

    }
}
