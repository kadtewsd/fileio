package com.kasakad.fileio.kasakaidfileio.domain.musicfestival;

import com.kasakad.fileio.kasakaidfileio.domain.EntityObject;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("OVERSEA")
@NoArgsConstructor
public class OverseaMusicFestival extends MusicFestival implements EntityObject<Integer> {

    public OverseaMusicFestival(int id, String name, LocalDateTime eventDate, MusicFestivalPlace musicFestivalPlace) {
        super(id, name, eventDate, musicFestivalPlace);
    }

    public OverseaMusicFestival(String name, LocalDateTime eventDate, MusicFestivalPlace musicFestivalPlace) {
        super(name, eventDate, musicFestivalPlace);
    }

    @Override
    public String festivalPlace() {
        return String.format("%s %s", getMusicFestivalPlace().getCountry(), getMusicFestivalPlace().getPlace());
    }
}
