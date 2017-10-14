package com.kasakad.fileio.kasakaidfileio.domain;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JacksonCSVFormatter {

    @SneakyThrows
    public <T> String write(T value) {
        CsvMapper csvPrinter = new CsvMapper();
        CsvSchema schema = csvPrinter.schemaFor(value.getClass());
        return csvPrinter.writer(schema).writeValueAsString(value);
    }

    @SneakyThrows
    public <T> String write(List<T> values) {
        StringBuilder stb = new StringBuilder();
        for (T line : values) {
            stb.append(write(line));
        }
        return stb.toString();
    }
}
