package com.kasakad.fileio.kasakaidfileio.service;

import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.utility.TestFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@Component
public class FileVerification {

    @Autowired
    protected TestFileReader reader;

    public String folderName() {
        return "music_festival";
    }

    public String fileName() {
        return "music_festival.csv";
    }

    public void verify(List<MusicFestival> musicFestivals) {
        List<TestMusicFestivalDTO> musicFestivalDTOList = reader.convertCSVFile2DTO(folderName(), fileName(), TestMusicFestivalDTO.class);
        for (MusicFestival musicFestival : musicFestivals) {
            TestMusicFestivalDTO source = findSame(musicFestival, musicFestivalDTOList);
            assertThat(source.getId(), is(musicFestival.getId()));
            assertThat(source.getName(), is(musicFestival.getName()));
            assertThat(source.getPlace(), is(musicFestival.getPlace()));
            assertThat(source.getEventDate(), is(musicFestival.getEventDate()));
        }
    }

    private TestMusicFestivalDTO findSame(MusicFestival musicFestival, List<TestMusicFestivalDTO> test) {
        return test.stream()
                .filter(x -> x.getId() == musicFestival.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("存在しないID" +  musicFestival.getId()));
    }

}
