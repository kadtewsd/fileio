package com.kasakad.fileio.kasakaidfileio.domain.service;

import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.web.InvalidInformation;
import com.kasakad.fileio.kasakaidfileio.web.ServiceResult;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class MusicFestivalServiceResult extends ServiceResult {

    private MusicFestival musicFestival;

    public MusicFestivalServiceResult(List<InvalidInformation> invalidInformation) {
        super(invalidInformation);
    }

    public MusicFestivalServiceResult(MusicFestival musicFestival) {
        super(new LinkedList<>());
        this.musicFestival = musicFestival;
    }
}
