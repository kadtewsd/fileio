package com.kasakad.fileio.kasakaidfileio.repository;

import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MusicFestivalRepositoryImpl implements MusicFestivalRepository {

    private final JpaMusicFestivalRepository repository;

    @Override
    public List<MusicFestival> findAll() {
        return repository.findAll();
    }
}
