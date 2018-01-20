package com.kasakad.fileio.kasakaidfileio.infrastructure.repository;

import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMusicFestivalRepository extends JpaRepository<MusicFestival, Integer> {
}
