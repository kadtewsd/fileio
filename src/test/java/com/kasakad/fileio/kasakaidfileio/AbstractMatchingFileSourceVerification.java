package com.kasakad.fileio.kasakaidfileio;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.kasakad.fileio.kasakaidfileio.utility.TestFileReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public abstract class AbstractMatchingFileSourceVerification<T> {

    @Autowired
    private TestFileReader reader;

    protected abstract String folderName();

    protected abstract String fileName();

    protected abstract int entityListSize();

    public void verifyEntity(List<T> entityList) {
        assertThat(verify(entityList), is(entityListSize()));
    }
    protected abstract int verify(List<T> entityList);

    protected abstract CsvSchema csvSchema();

    protected <Y> List<Y> getDTOFromFile(Class<Y> mappedClass) {
        return getDTOFromFile(folderName(), fileName(), mappedClass);
    }

    protected <Y> List<Y> getDTOFromFile(String folderName, String fileName, Class<Y> mappedClass) {
        return reader.convertCSVFile2DTO(folderName, fileName, mappedClass, csvSchema());
    }
}
