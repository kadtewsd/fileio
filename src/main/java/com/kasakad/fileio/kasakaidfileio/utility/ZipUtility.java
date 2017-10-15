package com.kasakad.fileio.kasakaidfileio.utility;

import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtility {

    private ZipOutputStream zip;
    private ByteArrayOutputStream result;
    public ZipUtility () {
        result = new ByteArrayOutputStream();
        this.zip = new ZipOutputStream(result);
    }

    @SneakyThrows
    public void write(String fileName, String content) {
        InputStream input = new ByteArrayInputStream(content.getBytes());
        ZipEntry entry = new ZipEntry(fileName);
        zip.putNextEntry(entry);
        byte[] buf = new byte[1024];
        for (int len; 0 < (len = input.read(buf)); ) {
            zip.write(buf, 0, len);
        }
        input.close();
    }

    @SneakyThrows
    private void close() {
        zip.closeEntry();
        zip.close();
        //close メソッドでは何もしていない
        //result.close();
    }

    public ByteArrayOutputStream getResult() {
        close();
        return result;
    }
}
