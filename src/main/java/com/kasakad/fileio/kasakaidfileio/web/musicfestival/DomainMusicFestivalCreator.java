package com.kasakad.fileio.kasakaidfileio.web.musicfestival;

import com.kasakad.fileio.kasakaidfileio.domain.Constants;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.DomainMusicFestival;
import com.kasakad.fileio.kasakaidfileio.web.EntityCreator;
import com.kasakad.fileio.kasakaidfileio.web.InvalidInformation;

import java.util.List;

public class DomainMusicFestivalCreator extends EntityCreator<MusicFestivalRequestDTO, DomainMusicFestival> {

    @Override
    public DomainMusicFestival createEntity(MusicFestivalRequestDTO mapped) {
        return new DomainMusicFestival(
                mapped.getName(),
                mapped.getEventDate(),
                mapped.getCity(),
                mapped.getPlace()
        );
    }

    @Override
    public List<InvalidInformation> validate(MusicFestivalRequestDTO mapped) {
        if ("日本".equals(mapped.getCountry())) return invalidInformation;
        this.addInvalidInformation(new InvalidInformation(
                String.format("%s を指定する場合は、%sだけ許可されます。",
                        MusicFestivalEntityType.DOMAIN.name(),
                        Constants.DOMAIN_COUNTRY)
        ));
        return invalidInformation;
    }
}
