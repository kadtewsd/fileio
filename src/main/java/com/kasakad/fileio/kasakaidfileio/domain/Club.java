package com.kasakad.fileio.kasakaidfileio.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("club")
public class Club extends Artist {

    public Club(int id, String artistName, int members) {
        super(id, artistName, members);
    }

    @Override
    public CSVOutputSpecification.FileNames getFileName() {
        return CSVOutputSpecification.FileNames.club;
    }
}
