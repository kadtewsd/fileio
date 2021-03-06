package com.kasakad.fileio.kasakaidfileio.domain.artist;

import com.kasakad.fileio.kasakaidfileio.domain.CSVOutputSpecification;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("rock")
public class Rock extends Artist {
    public Rock(int id, String artistName, int members) {
        super(id, artistName, members);
    }

    @Override
    public CSVOutputSpecification.FileNames getFileName() {
        return CSVOutputSpecification.FileNames.rock;
    }
}
