package com.kasakad.fileio.kasakaidfileio.repository;

import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMusicFestivalRepository extends JpaRepository<MusicFestival, Integer> {
}
