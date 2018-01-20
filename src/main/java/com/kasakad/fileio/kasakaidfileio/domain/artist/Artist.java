package com.kasakad.fileio.kasakaidfileio.domain.artist;

import com.kasakad.fileio.kasakaidfileio.domain.CSVOutputSpecification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Artist {
    @Id
    private int id;
    private String name;
    private int members;
    public abstract CSVOutputSpecification.FileNames getFileName();
}
