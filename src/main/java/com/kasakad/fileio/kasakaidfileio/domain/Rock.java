package com.kasakad.fileio.kasakaidfileio.domain;

public class Rock extends Artist {
    public Rock(String artistName, int members) {
        super(artistName, members);
    }

    @Override
    public CSVOutputSpecification.FileNames getFileName() {
        return CSVOutputSpecification.FileNames.rock;
    }
}
