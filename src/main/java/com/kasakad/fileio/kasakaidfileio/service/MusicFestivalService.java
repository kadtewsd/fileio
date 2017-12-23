package com.kasakad.fileio.kasakaidfileio.service;

import com.kasakad.fileio.kasakaidfileio.domain.CSVOutputSpecification;
import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.domain.fileoutput.MusicFestivalDTO;
import com.kasakad.fileio.kasakaidfileio.repository.MusicFestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MusicFestivalService {

    private final MusicFestivalRepository musiciansRepository;

    public ByteArrayOutputStream getMusicFestivalZip() {
        List<MusicFestival> musicFestivalList = musiciansRepository.findDummy();
        CSVOutputSpecification specification = new CSVOutputSpecification(
                createMusicFestivalDTO(musicFestivalList));
        return specification.export2CSV();
    }

    private List<MusicFestivalDTO> createMusicFestivalDTO(List<MusicFestival> musicFestivals) {
        List<MusicFestivalDTO> results = new LinkedList<>();
        musicFestivals.forEach(x -> results.addAll(x.createMusicFestivalCSV()));
        return results;
    }

    public List<MusicFestival> findAll() {
        return musiciansRepository.findAll();
    }
}
