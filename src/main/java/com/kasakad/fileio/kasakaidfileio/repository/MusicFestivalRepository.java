package com.kasakad.fileio.kasakaidfileio.repository;

import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;

import java.util.List;

public interface MusicFestivalRepository {

    List<MusicFestival> findAll();

    List<MusicFestival> findDummy();
}
