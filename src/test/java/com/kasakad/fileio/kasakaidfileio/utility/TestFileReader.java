package com.kasakad.fileio.kasakaidfileio.utility;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class TestFileReader {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TestInstanceCreator instanceCreator;

    @SneakyThrows
    public BufferedReader asReader(String folderName, String fileName) {
        return new BufferedReader(new InputStreamReader(
                applicationContext.getResource("classpath:" + toFilePath(folderName, fileName)).getInputStream()));
    }

    private String toFilePath(String folderName, String fileName) {
        return ("/testData/" + folderName + "/" + fileName).replace("Test", "");
    }

    @SneakyThrows
    public String toCSV(BufferedReader reader) {
        String line;
        StringBuilder stb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stb.append(line);
            stb.append("\n");
        }

        // 最終行の改行削除
        return stb.toString().trim();
    }

    @SneakyThrows
    public <T> List<T> convertCSVFile2DTO(String folderName, String fileName, Class<T> clz, CsvSchema csvSchema) {
        BufferedReader bufferedReader = asReader(folderName, fileName);
        String csvFile = toCSV(bufferedReader);

        CsvMapper csvMapper = instanceCreator.csvObjectMapper();
        ObjectReader reader = csvMapper.readerFor(clz).with(csvSchema.withHeader());
        // 順番を決めるためには @JsonPropertyOrder アノテーションが必要
//        CsvSchema schema = csvMapper.schemaFor(clz).withHeader();
//        MappingIterator<T> it = csvMapper.readerFor(clz)
//                .with(schema)
//                .readValues(csvFile);
        MappingIterator<T> it = reader.readValues(csvFile);
        return it.readAll();
    }
}
