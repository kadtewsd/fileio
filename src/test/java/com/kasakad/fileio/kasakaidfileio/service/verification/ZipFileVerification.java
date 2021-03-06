package com.kasakad.fileio.kasakaidfileio.service.verification;

import com.kasakad.fileio.kasakaidfileio.domain.JacksonCSVFormatter;
import com.kasakad.fileio.kasakaidfileio.domain.artist.Club;
import com.kasakad.fileio.kasakaidfileio.domain.artist.Rock;
import com.kasakad.fileio.kasakaidfileio.domain.fileoutput.MusicFestivalDTO;
import com.kasakad.fileio.kasakaidfileio.utility.ByteArrayOutputVerification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@Slf4j
@Component
public class ZipFileVerification implements ByteArrayOutputVerification {

    @Autowired
    private JacksonCSVFormatter formatter;

    private static final LocalDateTime eventDate = LocalDateTime.of(2017, 4, 5, 9,0,0);

    private List<MusicFestivalDTO> rock = new LinkedList<MusicFestivalDTO>() {
        {
            add(new MusicFestivalDTO(2, "METROCK 2016", eventDate, "新木場岩洲公園", new Rock(12, "ゲスの極み乙女", 4), 7));
            add(new MusicFestivalDTO(3, "ROCK IN JAPAN FESTIVAL 2017", eventDate, "国営ひたち海浜公園", new Rock(14, "ゴールデンボンバー", 5), 1));
            add(new MusicFestivalDTO(3, "ROCK IN JAPAN FESTIVAL 2017", eventDate, "国営ひたち海浜公園", new Rock(15, "Dragon Ash", 7), 2));
        }
    };

    private List<MusicFestivalDTO> club = new LinkedList<MusicFestivalDTO>() {
        {
            add(new MusicFestivalDTO(2, "METROCK 2016", eventDate, "新木場岩洲公園", new Club(13, "サカナクション", 5), 8));
            add(new MusicFestivalDTO(3, "ROCK IN JAPAN FESTIVAL 2017", eventDate, "国営ひたち海浜公園", new Club(16, "水曜日のカンパネラ", 1), 3));
        }
    };
    private String clubExpected;
    private String rockExpected;

    @Override
    public void readyToVerify() {
        clubExpected = formatter.write(club);
        rockExpected = formatter.write(rock);
    }

    @Override
    public int verifyRock(String zipString) {
        log.info("rock is \n {}", zipString);
        assertThat(zipString, is(rockExpected));
        return 1;
    }

    @Override
    public int verifyClub(String zipString) {
        log.info("club is \n {}", zipString);
        assertThat(zipString, is(clubExpected));
        return 1;
    }
}
