package com.kasakad.fileio.kasakaidfileio.domain;

import com.kasakad.fileio.kasakaidfileio.domain.fileoutput.MusicFestivalDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class MusicFestival {

    public MusicFestival(int id, String name, String place, LocalDateTime eventDate) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.eventDate = eventDate;
    }

    @Id
    private int id;

    @Transient
    private List<ArtistPlayOrder> artists = new LinkedList<>();

    private String name;

    private String place;

    private LocalDateTime eventDate;

    public List<MusicFestivalDTO> createMusicFestivalCSV() {
        // 順番が保証される必要があるので、List で返す。
//        Set<MusicFestivalDTO> results = new HashSet<>();
        List<MusicFestivalDTO> results = new LinkedList<>();
        artists.forEach(x -> {
            results.add(new MusicFestivalDTO(this.id, this.name, this.place, x.getArtist(), x.getPlayOrder()));

        });

        return results;
    }

    public void setArtist(Artist artist, int playOrder) {
        this.artists.add(new ArtistPlayOrder(artist, playOrder));
    }
}
