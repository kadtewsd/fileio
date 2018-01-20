package com.kasakad.fileio.kasakaidfileio.domain;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.kasakad.fileio.kasakaidfileio.utility.InstanceCreator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JacksonCSVFormatter {

    @Autowired
    private InstanceCreator creator;

    @SneakyThrows
    public <T> String write(T value) {
        CsvMapper csvPrinter = new CsvMapper();
        CsvSchema schema = csvPrinter.schemaFor(value.getClass()).withHeader();
        return csvPrinter.writer(schema).writeValueAsString(value);
    }

    @SneakyThrows
    public <T> String write(List<T> values) {
        CsvMapper csvPrinter = creator.csvObjectMapper();
        // テストクラスから実行した場合、values の型は何故かテストクラスになる。
//        CsvSchema schema = csvPrinter.schemaFor(values.getClass().getTypeParameters()[0].getClass()).withHeader();
        CsvSchema schema = csvPrinter.schemaFor(values.get(0).getClass()).withHeader();
        return csvPrinter.writer(schema).writeValueAsString(values);
    }
}
