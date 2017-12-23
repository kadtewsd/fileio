package com.kasakad.fileio.kasakaidfileio.service;

import com.kasakad.fileio.kasakaidfileio.AbstractBaseTest;
import com.kasakad.fileio.kasakaidfileio.domain.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.service.verification.FileVerification;
import com.kasakad.fileio.kasakaidfileio.service.verification.ZipFileVerification;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.util.List;

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
    public void getMusicFestivalZip() throws Exception {
        ByteArrayOutputStream zip = service.getMusicFestivalZip();
        zipFileVerification.verifyCSV(zip);
        zipFileVerification.output(zip);
    }

    @SneakyThrows
    @Test
    public void インプットと取得内容が同一である検証() {
        myResource.insertData("music_festival");
        List<MusicFestival> festivals = service.findAll();
        fileVerification.verifyEntity(festivals);
    }
}