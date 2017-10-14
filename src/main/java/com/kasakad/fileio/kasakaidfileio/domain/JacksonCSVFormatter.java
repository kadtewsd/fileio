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
        CsvSchema schema = csvPrinter.schemaFor(value.getClass()).withHeader();
        return csvPrinter.writer(schema).writeValueAsString(value);
    }

    @SneakyThrows
    public <T> String write(List<T> values) {
        CsvMapper csvPrinter = new CsvMapper();
        // テストクラスから実行した場合、values の型は何故かテストクラスになる。
//        CsvSchema schema = csvPrinter.schemaFor(values.getClass().getTypeParameters()[0].getClass()).withHeader();
        CsvSchema schema = csvPrinter.schemaFor(values.get(0).getClass()).withHeader();
        return csvPrinter.writer(schema).writeValueAsString(values);
    }
}
