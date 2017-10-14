package com.kasakad.fileio.kasakaidfileio.domain;

public class Club extends Artist {
    public Club(String artistName, int members) {
        super(artistName, members);
    }

    @Override
    public CSVOutputSpecification.FileNames getFileName() {
        return CSVOutputSpecification.FileNames.club;
    }
}
