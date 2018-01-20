package com.kasakad.fileio.kasakaidfileio.web.musicfestival;

import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestivalPlace;
import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.OverseaMusicFestival;
import com.kasakad.fileio.kasakaidfileio.web.EntityCreator;
import com.kasakad.fileio.kasakaidfileio.web.InvalidInformation;

import java.util.List;

import static com.kasakad.fileio.kasakaidfileio.domain.Constants.*;

public class OverseaEntityCreator extends EntityCreator<MusicFestivalRequestDTO, OverseaMusicFestival> {

    @Override
    public OverseaMusicFestival createEntity(MusicFestivalRequestDTO mapped) {
        return new OverseaMusicFestival(
                mapped.getName(),
                mapped.getEventDate(),
                new MusicFestivalPlace(
                        mapped.getCountry(),
                        mapped.getCity(),
                        mapped.getPlace()
                )
        );
    }

    @Override
    public List<InvalidInformation> validate(MusicFestivalRequestDTO mapped) {
        if (!"日本".equals(mapped.getCountry())) return invalidInformation;
        this.addInvalidInformation(new InvalidInformation(
                String.format("%s を指定する場合は、%s以外が許可されます。",
                        MusicFestivalEntityType.OVERSEA.name(),
                        DOMAIN_COUNTRY)
        ));
        return invalidInformation;
    }
}
