package com.kasakad.fileio.kasakaidfileio.repository;

import com.kasakad.fileio.kasakaidfileio.domain.Club;
import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.domain.Rock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MusicFestivalRepositoryImpl implements MusicFestivalRepository {

    private final JpaMusicFestivalRepository repository;

    @Override
    public List<MusicFestival> findAll() {
        return repository.findAll();
    }

    public List<MusicFestival> findDummy() {

        MusicFestival metrock = new MusicFestival(2, "METROCK 2016", "新木場岩洲公園", LocalDateTime.of(2016, 5, 21, 13, 00, 00));
        metrock.setArtist(new Rock(12,"ゲスの極み乙女", 4), 7);
        metrock.setArtist(new Club(13,"サカナクション", 5), 8);

        MusicFestival rockin = new MusicFestival(3, "ROCK IN JAPAN FESTIVAL 2017", "国営ひたち海浜公園", LocalDateTime.of(2017, 8, 5, 10, 00, 00));
        rockin.setArtist(new Rock(14, "ゴールデンボンバー", 5), 1);
        rockin.setArtist(new Rock(15, "Dragon Ash", 7),2);
        rockin.setArtist(new Club(16, "水曜日のカンパネラ", 1), 3);

        List<MusicFestival> result = new LinkedList<MusicFestival>() // この書き方でリストを初期化する場合は、具象クラスにも型パラメーターを指定する
        {
            {
                add(metrock);
                add(rockin);
            }
        };
        return result;
    }
}
