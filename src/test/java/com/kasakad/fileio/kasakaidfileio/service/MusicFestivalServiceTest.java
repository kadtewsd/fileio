package com.kasakad.fileio.kasakaidfileio.service;

import com.kasakad.fileio.kasakaidfileio.AbstractBaseTest;
import com.kasakad.fileio.kasakaidfileio.domain.artist.Club;
import com.kasakad.fileio.kasakaidfileio.domain.artist.Rock;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.DomainMusicFestival;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestivalRepository;
import com.kasakad.fileio.kasakaidfileio.domain.service.MusicFestivalService;
import com.kasakad.fileio.kasakaidfileio.service.verification.FileVerification;
import com.kasakad.fileio.kasakaidfileio.service.verification.ZipFileVerification;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@Slf4j
public class MusicFestivalServiceTest extends AbstractBaseTest {

    @Autowired
    private MusicFestivalService service;

    @Autowired
    private ZipFileVerification zipFileVerification;

    @Autowired
    private FileVerification fileVerification;

    @Before
    public void setUp() {
        super.setup();
    }

    @Test
    public void Mockitoでスタブを作りZipを検証() throws Exception {
        MusicFestival metrock = new DomainMusicFestival(
                2,
                "METROCK 2016",
                LocalDateTime.of(2016, 5, 21, 13, 00, 00),
                "東京都",
                "新木場岩洲公園");
        metrock.setArtist(new Rock(12, "ゲスの極み乙女", 4), 7);
        metrock.setArtist(new Club(13, "サカナクション", 5), 8);

        MusicFestival rockin = new DomainMusicFestival(3,
                "ROCK IN JAPAN FESTIVAL 2017",
                LocalDateTime.of(2017, 8, 5, 10, 00, 00),
                "茨城県",
                "国営ひたち海浜公園");
        rockin.setArtist(new Rock(14, "ゴールデンボンバー", 5), 1);
        rockin.setArtist(new Rock(15, "Dragon Ash", 7), 2);
        rockin.setArtist(new Club(16, "水曜日のカンパネラ", 1), 3);

        MusicFestivalRepository repository = mock(MusicFestivalRepository.class);
        List<MusicFestival> result = new LinkedList<MusicFestival>() // この書き方でリストを初期化する場合は、具象クラスにも型パラメーターを指定する
        {
            {
                add(metrock);
                add(rockin);
            }
        };

        Mockito.when(repository.findAll())
                .thenReturn(result);

        ByteArrayOutputStream zip = service.getMusicFestivalZip();
        zipFileVerification.verifyCSV(zip);
        zipFileVerification.output(zip);

        Mockito.reset(repository);

    }

    @SneakyThrows
    @Test
    public void インプットと取得内容が同一である検証() {
        myResource.insertData("music_festival");
        List<MusicFestival> festivals = service.getAllMusicFestivals();
        fileVerification.verifyEntity(festivals);
    }
}