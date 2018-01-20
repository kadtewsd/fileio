package com.kasakad.fileio.kasakaidfileio.service.verification;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.kasakad.fileio.kasakaidfileio.AbstractMatchingFileSourceVerification;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.web.musicfestival.MusicFestivalRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@Component
public class FileVerification extends AbstractMatchingFileSourceVerification<MusicFestival> {

    protected String folderName() {
        return "music_festival";
    }

    protected String fileName() {
        return "music_festival.csv";
    }

    @Override
    protected int entityListSize() {
        return 3;
    }

    protected int verify(List<MusicFestival> musicFestivals) {
        List<MusicFestivalRequestDTO> musicFestivalDTOList = getDTOFromFile(MusicFestivalRequestDTO.class);
        int result = 0;
        for (MusicFestival musicFestival : musicFestivals) {
            MusicFestivalRequestDTO source = findSame(musicFestival, musicFestivalDTOList);
            assertThat(source.getName(), is(musicFestival.getName()));
            assertThat(source.getEventDate(), is(musicFestival.getEventDate()));
            assertThat(source.getCountry(), is(musicFestival.getMusicFestivalPlace().getCountry()));
            assertThat(source.getCity(), is(musicFestival.getMusicFestivalPlace().getCity()));
            assertThat(source.getPlace(), is(musicFestival.getMusicFestivalPlace().getPlace()));
            result++;
        }

        return result;
    }

    /**
     * DTO : eventDate
     * DB (CSV) : event_date
     * なので、シリアル化する時は明示的に DTO のフィールド名を指定しつつ、
     * それが何番目に存在するかという情報を与えないと、うまくマップできない。
     * @return
     */
    @Override
    protected CsvSchema csvSchema() {
        return  CsvSchema.builder()
                .addColumn("id")
                .addColumn("name")
                .addColumn(new CsvSchema.Column(3, "eventDate"))
                .addColumn("country")
                .addColumn("city")
                .addColumn("place")
                .addColumn("dtype")
                .build();
    }

    private MusicFestivalRequestDTO findSame(MusicFestival musicFestival, List<MusicFestivalRequestDTO> test) {
        return test.stream()
                .filter(x -> x.getName().equals(musicFestival.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("存在しないID [%d]", musicFestival.getId())));
    }

}
