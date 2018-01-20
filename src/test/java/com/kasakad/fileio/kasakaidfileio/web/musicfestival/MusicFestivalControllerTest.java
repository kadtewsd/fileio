package com.kasakad.fileio.kasakaidfileio.web.musicfestival;

import com.kasakad.fileio.kasakaidfileio.AbstractRestTest;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.DomainMusicFestival;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestivalRepository;
import com.kasakad.fileio.kasakaidfileio.infrastructure.Parser;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class MusicFestivalControllerTest extends AbstractRestTest {

    @Autowired
    private Parser parser;

    @Autowired
    private MusicFestivalRepository repository;

    @Before
    public void setUp() {
        super.setUp();
    }

    private static final String url = "/api/music_festival";

    private static final LocalDateTime eventDate = LocalDateTime.of(2018, 2, 3, 9, 3, 39);

    @SneakyThrows
    @Test
    public void 国内フェス作成() {
        MusicFestivalRequestDTO musicFestivalRequestDTO = 国内テスト用データ();
        String json = objectMapper.writeValueAsString((musicFestivalRequestDTO));
        System.out.println(json);
        mvc.perform(
                MockMvcRequestBuilders.post(url)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("ミュージックフェスティバルが正常に登録されました。")))
                .andExpect(jsonPath("$.musicFestival.id", is(1)))
                .andExpect(jsonPath("$.musicFestival.name", is(musicFestivalRequestDTO.getName())))
                .andExpect(jsonPath("$.musicFestival.eventDate", is(parser.deserializeByISOStandard(musicFestivalRequestDTO.getEventDate()))))
                .andExpect(jsonPath("$.musicFestival.musicFestivalPlace.country", is(musicFestivalRequestDTO.getCountry())))
                .andExpect(jsonPath("$.musicFestival.musicFestivalPlace.city", is(musicFestivalRequestDTO.getCity())))
                .andExpect(jsonPath("$.musicFestival.musicFestivalPlace.place", is(musicFestivalRequestDTO.getPlace())))
        ;
        List<MusicFestival> musicFestivalList = repository.findAll();

        assertThat(musicFestivalList.size(), is(1));
        MusicFestival musicFestival = musicFestivalList.get(0);
        assertThat(musicFestival.getClass(), typeCompatibleWith(DomainMusicFestival.class));
        assertThat(musicFestival.getId(), is(1));
        assertThat(musicFestival.getName(), is(musicFestivalRequestDTO.getName()));
        assertThat(musicFestival.getEventDate(), is(musicFestivalRequestDTO.getEventDate()));
        assertThat(musicFestival.getMusicFestivalPlace().getCountry(), is(musicFestivalRequestDTO.getCountry()));
        assertThat(musicFestival.getMusicFestivalPlace().getCity(), is(musicFestivalRequestDTO.getCity()));
        assertThat(musicFestival.getMusicFestivalPlace().getPlace(), is(musicFestivalRequestDTO.getPlace()));
    }

    private MusicFestivalRequestDTO 国内テスト用データ() {
        return new MusicFestivalRequestDTO(
                "フジロック",
                eventDate,
                "日本",
                "東京都",
                "東京ベイサイドスクエア",
                MusicFestivalEntityType.DOMAIN
        );
    }
}