package com.kasakad.fileio.kasakaidfileio.domain.musicfestival;

import com.kasakad.fileio.kasakaidfileio.domain.EntityObject;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

import static com.kasakad.fileio.kasakaidfileio.domain.Constants.*;

@Entity
@DiscriminatorValue("DOMAIN")
@NoArgsConstructor
public class DomainMusicFestival extends MusicFestival implements EntityObject {

    public DomainMusicFestival(int id, String name, LocalDateTime eventDate, String city, String place) {
        super(id, name, eventDate, new MusicFestivalPlace(DOMAIN_COUNTRY, city, place));
    }

    public DomainMusicFestival(String name, LocalDateTime eventDate, String city, String place) {
        super(name, eventDate, new MusicFestivalPlace(DOMAIN_COUNTRY, city, place));
    }

    @Override
    public String festivalPlace() {
        return String.format("%s %s", getMusicFestivalPlace().getCity(), getMusicFestivalPlace().getPlace());
    }
}
