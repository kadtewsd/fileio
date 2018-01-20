package com.kasakad.fileio.kasakaidfileio.web.musicfestival;

import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.web.EntityCreator;
import com.kasakad.fileio.kasakaidfileio.web.MappedDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MusicFestivalRequestDTO extends MappedDTO {

    @NotNull
    @Getter
    private final String name;

    @Getter
    private final LocalDateTime eventDate;

    @NotNull
    @Getter
    private final String country;

    @NotNull
    @Getter
    private final String city;

    @NotNull
    @Getter
    private final String place;

    @NotNull
    private final MusicFestivalEntityType entityType;

    public EntityCreator<MusicFestivalRequestDTO, ? extends MusicFestival> getCreator() {
        return this.entityType.getCreator();
    }
}
