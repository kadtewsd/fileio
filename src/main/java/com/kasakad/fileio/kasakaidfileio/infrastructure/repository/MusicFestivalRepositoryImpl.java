package com.kasakad.fileio.kasakaidfileio.infrastructure.repository;

import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestivalRepository;
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

    @Override
    public MusicFestival save(MusicFestival musicFestival) {
        return repository.save(musicFestival);
    }
}
