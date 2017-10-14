package com.kasakad.fileio.kasakaidfileio.domain;

import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CSVOutputSpecification {

    public enum FileNames {
        rock,
        club,
        heavymetal
    }

    private JacksonCSVFormatter formatter;

    private List<MusicFestival> musicFestivals;
    private Map<FileNames, StringBuilder> builders = new HashMap<>();

    public CSVOutputSpecification(List<MusicFestival> list) {
        this.musicFestivals = list;
        this.builders.put(FileNames.rock, new StringBuilder());
        this.builders.put(FileNames.club, new StringBuilder());
        this.builders.put(FileNames.heavymetal, new StringBuilder());
        this.formatter = new JacksonCSVFormatter();
    }

    @SneakyThrows
    public ByteArrayOutputStream export2CSV() {
        // Bean をシリアライズすると、各行でヘッダーが出る。そのため、リストで丸ごとシリアライズする。
        List<MusicFestival> rock = musicFestivals.stream().filter(musicFestival -> musicFestival.getArtist().getFileName() == FileNames.rock).collect(Collectors.toList());
        append(rock);
        List<MusicFestival> club = musicFestivals.stream().filter(musicFestival -> musicFestival.getArtist().getFileName() == FileNames.club).collect(Collectors.toList());
        append(club);

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(result);
        for (Map.Entry<FileNames, StringBuilder> entry : builders.entrySet()) {
            if (entry.getValue().length() != 0) {
                write(entry, zip);
            }
        }
        zip.closeEntry();
        zip.close();
        return result;
    }


    private void append(List<MusicFestival> musicFestival) {
        if (musicFestival.size() == 0) return;
        StringBuilder stb = this.builders.get(musicFestival.get(0).getArtist().getFileName());
        stb.append(formatter.write(musicFestival));
    }

    @SneakyThrows
    private void write(Map.Entry<FileNames, StringBuilder> target, ZipOutputStream zip) {
        InputStream input = new ByteArrayInputStream(target.getValue().toString().getBytes());
        ZipEntry entry = new ZipEntry(target.getKey().name() + ".csv");
        zip.putNextEntry(entry);
        byte[] buf = new byte[1024];
        for (int len; 0 < (len = input.read(buf)); ) {
            zip.write(buf, 0, len);
        }
        input.close();
    }
}
