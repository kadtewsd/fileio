package com.kasakad.fileio.kasakaidfileio.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Artist {
    private final String artistName;
    private final int members;
    public abstract CSVOutputSpecification.FileNames getFileName();
}
