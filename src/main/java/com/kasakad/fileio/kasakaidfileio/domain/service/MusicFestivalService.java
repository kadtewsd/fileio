package com.kasakad.fileio.kasakaidfileio.domain.service;

import com.kasakad.fileio.kasakaidfileio.domain.CSVOutputSpecification;
import com.kasakad.fileio.kasakaidfileio.domain.fileoutput.MusicFestivalDTO;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestivalRepository;
import com.kasakad.fileio.kasakaidfileio.web.InvalidInformation;
import com.kasakad.fileio.kasakaidfileio.web.musicfestival.MusicFestivalRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class MusicFestivalService {

    private final MusicFestivalRepository musiciansRepository;

    public ByteArrayOutputStream getMusicFestivalZip() {
        List<MusicFestival> musicFestivalList = musiciansRepository.findAll();
        CSVOutputSpecification specification = new CSVOutputSpecification(
                createMusicFestivalDTO(musicFestivalList));
        return specification.export2CSV();
    }

    private List<MusicFestivalDTO> createMusicFestivalDTO(List<MusicFestival> musicFestivals) {
        List<MusicFestivalDTO> results = new LinkedList<>();
        musicFestivals.forEach(x -> results.addAll(x.createMusicFestivalCSV()));
        return results;
    }

    public List<MusicFestival> getAllMusicFestivals() {
        return musiciansRepository.findAll();
    }

    public MusicFestivalServiceResult register(MusicFestivalRequestDTO musicFestivalDTO) {

        List<InvalidInformation> invalidInformation = musicFestivalDTO.validate();

        if (invalidInformation.isEmpty()) {
            return new MusicFestivalServiceResult(
                musiciansRepository.save(
                        musicFestivalDTO.createEntity()
                )
            );
        }
        return new MusicFestivalServiceResult(invalidInformation);
    }
}
