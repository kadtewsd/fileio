package com.kasakad.fileio.kasakaidfileio.utility;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class ZipUtility {
    @SneakyThrows
    public void write(String fileName, String content, ZipOutputStream zip) {
        InputStream input = new ByteArrayInputStream(content.getBytes());
        ZipEntry entry = new ZipEntry(fileName);
        zip.putNextEntry(entry);
        byte[] buf = new byte[1024];
        for (int len; 0 < (len = input.read(buf)); ) {
            zip.write(buf, 0, len);
        }
        input.close();
    }
}
