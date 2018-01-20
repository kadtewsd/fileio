package com.kasakad.fileio.kasakaidfileio.domain.musicfestival;

import java.util.List;

public interface MusicFestivalRepository {

    List<MusicFestival> findAll();

    MusicFestival save(MusicFestival musicFestival);
}
