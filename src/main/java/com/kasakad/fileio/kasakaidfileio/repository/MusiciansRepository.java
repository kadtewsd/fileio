package com.kasakad.fileio.kasakaidfileio.repository;

import com.kasakad.fileio.kasakaidfileio.domain.Club;
import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.domain.Rock;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class MusiciansRepository {

    public List<MusicFestival> findAll() {
        List<MusicFestival> result = new LinkedList<MusicFestival>() // この書き方でリストを初期化する場合は、具象クラスにも型パラメーターを指定する
        {
            {
                add(new MusicFestival("ROCK IN JAPAN FESTIVAL 2017", new Rock("ゴールデンボンバー", 5), 1));
                add(new MusicFestival("ROCK IN JAPAN FESTIVAL 2017", new Rock("Dragon Ash", 7), 2));
                add(new MusicFestival("ROCK IN JAPAN FESTIVAL 2017", new Club("水曜日のカンパネラ", 3), 5));
                add(new MusicFestival("METROCK 2016", new Rock("ゲスの極み乙女", 4), 7));
                add(new MusicFestival("METROCK 2016", new Club("サカナクション", 5), 8));
            }
        };
        return result;
    }
}
