package com.kasakad.fileio.kasakaidfileio.service;

import com.kasakad.fileio.kasakaidfileio.KasakaidfileioApplication;
import com.kasakad.fileio.kasakaidfileio.domain.Club;
import com.kasakad.fileio.kasakaidfileio.domain.JacksonCSVFormatter;
import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.domain.Rock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = {KasakaidfileioApplication.class})
@RunWith(SpringRunner.class)
@Slf4j
public class MusicFestivalServiceTest {

    @Autowired
    private MusicFestivalService service;

    private JacksonCSVFormatter formatter = new JacksonCSVFormatter();

    private List<MusicFestival> rock = new LinkedList<MusicFestival>() {
        {
            add(new MusicFestival("ROCK IN JAPAN FESTIVAL 2017", new Rock("ゴールデンボンバー", 5), 1));
            add(new MusicFestival("ROCK IN JAPAN FESTIVAL 2017", new Rock("Dragon Ash", 7), 2));
            add(new MusicFestival("METROCK 2016", new Rock("ゲスの極み乙女", 4), 7));
        }
    };

    private List<MusicFestival> club = new LinkedList<MusicFestival>() {
        {
            add(new MusicFestival("ROCK IN JAPAN FESTIVAL 2017", new Club("水曜日のカンパネラ", 3), 5));
            add(new MusicFestival("METROCK 2016", new Club("サカナクション", 5), 8));
        }
    };

    private String clubExpected;
    private String rockExpected;

    @Before
    public void setUp() {
        clubExpected = formatter.write(club);
        rockExpected = formatter.write(rock);
    }

    @Test
    public void getMusicFestivalZip() throws Exception {
        ByteArrayOutputStream zip = service.getMusicFestivalZip();
        verifyCSV(zip);
    }

    public void verifyCSV(ByteArrayOutputStream output) {
        // 以下、zipを展開して、中身を確認する
        try (ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(output.toByteArray()))) {
            byte[] buffer = new byte[1024];
            ZipEntry zipEntry = zipIn.getNextEntry();
            int size;
            while (0 < (size = zipIn.read(buffer))) {
                log.info("file name is {}", zipEntry.getName());
                String zipString = new String(Arrays.copyOf(buffer, size));
                if (zipString.contains("rock")) {
                    log.info("rock is \n {}", zipString);
                    assertThat(zipEntry.getName(), is("rock.csv"));
                    assertThat(zipString, is(rockExpected));
                } else {
                    log.info("club is \n {}", zipString);
                    assertThat(zipString, is(clubExpected));
                }
                zipEntry = zipIn.getNextEntry();
            }
            zipIn.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}