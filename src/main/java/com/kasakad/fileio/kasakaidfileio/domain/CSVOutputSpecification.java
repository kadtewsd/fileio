package com.kasakad.fileio.kasakaidfileio.domain;

import com.kasakad.fileio.kasakaidfileio.utility.ZipUtility;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

public class CSVOutputSpecification {

    public enum FileNames {
        rock,
        club,
        heavymetal
    }

    private JacksonCSVFormatter formatter;

    private List<MusicFestival> musicFestivals;
    private Map<FileNames, StringBuilder> builders = new HashMap<FileNames, StringBuilder>() {
        {
            put(FileNames.rock, new StringBuilder());
            put(FileNames.club, new StringBuilder());
            put(FileNames.heavymetal, new StringBuilder());
        }
    };

    private ZipUtility zipUtility;

    public CSVOutputSpecification(List<MusicFestival> list) {
        this.musicFestivals = list;
        this.formatter = new JacksonCSVFormatter();
        this.zipUtility = new ZipUtility();
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
            write(entry, zip);
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
        if (target.getValue().length() == 0) return;
        zipUtility.write(target.getKey() + ".csv", target.getValue().toString(), zip);
    }
}
