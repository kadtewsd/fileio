package com.kasakad.fileio.kasakaidfileio.service;

import com.kasakad.fileio.kasakaidfileio.domain.CSVOutputSpecification;
import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.repository.MusiciansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MusicFestivalService {

    private final MusiciansRepository musiciansRepository;

    public ByteArrayOutputStream getMusicFestivalZip() {
        List<MusicFestival> musicFestivalList = musiciansRepository.findAll();
        CSVOutputSpecification specification = new CSVOutputSpecification(musicFestivalList);
        return specification.export2CSV();
    }
}
