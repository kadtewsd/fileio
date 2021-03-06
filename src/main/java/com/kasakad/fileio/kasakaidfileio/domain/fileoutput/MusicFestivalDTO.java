package com.kasakad.fileio.kasakaidfileio.domain.fileoutput;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kasakad.fileio.kasakaidfileio.domain.artist.Artist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@JsonPropertyOrder(value = {"フェス名", "日時", "開催場所", "アーティスト名", "メンバー数", "順番", "ジャンル"})
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MusicFestivalDTO {

    public MusicFestivalDTO(int id, String name, LocalDateTime eventDate, String place, Artist artist, int playOrder) {
        this.artist = artist;
        this.eventDate = eventDate;
        this.name = name;
        this.place = place;
        this.playOrder = playOrder;
        this.id = id;
    }

    @JsonIgnore
    private Artist artist;

    @JsonIgnore
    private int id;

    @JsonProperty("フェス名")
    private final String name;

    @JsonProperty("日時")
    private final LocalDateTime eventDate;

    @JsonProperty("開催場所")
    private final String place;

    @JsonProperty("順番")
    private final int playOrder;

    @JsonProperty("アーティスト名")
    public final String getArtistName() {
        return artist.getName();
    }

    @JsonProperty("メンバー数")
    public final int getMembers() {
        return artist.getMembers();
    }

    @JsonProperty("ジャンル")
    public final String getFileName() {
        return artist.getFileName().name();
    }
}
