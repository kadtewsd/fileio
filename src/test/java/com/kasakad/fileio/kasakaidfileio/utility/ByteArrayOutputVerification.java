package com.kasakad.fileio.kasakaidfileio.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public interface ByteArrayOutputVerification {

    void readyToVerify();

    int verifyRock(String zipString);
    int verifyClub(String zipString);

    default void verifyCSV(ByteArrayOutputStream output) {
        // 以下、zipを展開して、中身を確認する
        try (ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(output.toByteArray()))) {
            byte[] buffer = new byte[1024];
            ZipEntry zipEntry = zipIn.getNextEntry();
            int size;
            while (0 < (size = zipIn.read(buffer))) {
                String zipString = new String(Arrays.copyOf(buffer, size));
                if (zipString.contains("rock")) {
                    assertThat(zipEntry.getName(), is("rock.csv"));
                    verifyRock(zipString);
                } else {
                    verifyClub(zipString);
                }
                zipEntry = zipIn.getNextEntry();
            }
            zipIn.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default void output(ByteArrayOutputStream byteArrayOutputStream) {
        final String DIRECTORY = System.getProperty("user.home") + "/work";
        final String FILENAME = DIRECTORY + "/artist.zip";
        Path dir = Paths.get(DIRECTORY);
        try {
            if (!Files.isDirectory(dir)) {
                Files.createDirectory(dir);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Path path = Paths.get(FILENAME);
        try {
            Files.write(path, byteArrayOutputStream.toByteArray(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
