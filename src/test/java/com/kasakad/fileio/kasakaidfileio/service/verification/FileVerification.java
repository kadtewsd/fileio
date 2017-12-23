package com.kasakad.fileio.kasakaidfileio.service.verification;

import com.kasakad.fileio.kasakaidfileio.AbstractMatchingFileSourceVerification;
import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.service.TestMusicFestivalDTO;
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
        List<TestMusicFestivalDTO> musicFestivalDTOList = getDTOFromFile(TestMusicFestivalDTO.class);
        int result = 0;
        for (MusicFestival musicFestival : musicFestivals) {
            TestMusicFestivalDTO source = findSame(musicFestival, musicFestivalDTOList);
            assertThat(source.getId(), is(musicFestival.getId()));
            assertThat(source.getName(), is(musicFestival.getName()));
            assertThat(source.getPlace(), is(musicFestival.getPlace()));
            assertThat(source.getEventDate(), is(musicFestival.getEventDate()));
            result++;
        }

        return result;
    }

    private TestMusicFestivalDTO findSame(MusicFestival musicFestival, List<TestMusicFestivalDTO> test) {
        return test.stream()
                .filter(x -> x.getId() == musicFestival.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("存在しないID" + musicFestival.getId()));
    }

}
