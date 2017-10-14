package com.kasakad.fileio.kasakaidfileio.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonPropertyOrder(value = {"フェス名", "アーティスト名", "メンバー数", "順番", "ジャンル"})
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MusicFestival {

    public MusicFestival(String name, Artist artist, int playOrder) {
        this.artist = artist;
        this.festivalName = name;
        this.playOrder = playOrder;
    }

    @JsonIgnore
    private Artist artist;

    @JsonProperty("フェス名")
    private final String festivalName;

    @JsonProperty("順番")
    private final int playOrder;

    @JsonProperty("アーティスト名")
    public final String getArtistName() {
        return artist.getArtistName();
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
