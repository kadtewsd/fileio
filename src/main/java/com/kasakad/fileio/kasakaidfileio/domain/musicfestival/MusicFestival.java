package com.kasakad.fileio.kasakaidfileio.domain.musicfestival;

import com.kasakad.fileio.kasakaidfileio.domain.artist.Artist;
import com.kasakad.fileio.kasakaidfileio.domain.fileoutput.MusicFestivalDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MusicFestival {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false )
    private String name;

    @Column(nullable = false)
    private LocalDateTime eventDate;

    @Embedded
    private MusicFestivalPlace musicFestivalPlace;

    public MusicFestival(int id, String name, LocalDateTime eventDate, MusicFestivalPlace musicFestivalPlace) {
        this(name, eventDate, musicFestivalPlace);
        this.id = id;
    }

    public MusicFestival(String name, LocalDateTime eventDate, MusicFestivalPlace musicFestivalPlace) {
        this.name = name;
        this.eventDate = eventDate;
        this.musicFestivalPlace = musicFestivalPlace;
    }

    @Transient
    private List<ArtistPlayOrder> artists = new LinkedList<>();

    public List<MusicFestivalDTO> createMusicFestivalCSV() {
        // 順番が保証される必要があるので、List で返す。
//        Set<MusicFestivalRequestDTO> results = new HashSet<>();
        List<MusicFestivalDTO> results = new LinkedList<>();
        artists.forEach(x -> {
            results.add(new MusicFestivalDTO(this.id, this.name, this.eventDate, this.festivalPlace(), x.getArtist(), x.getPlayOrder()));

        });

        return results;
    }

    public void setArtist(Artist artist, int playOrder) {
        this.artists.add(new ArtistPlayOrder(artist, playOrder));
    }

    public abstract String festivalPlace();
}
