package com.kasakad.fileio.kasakaidfileio.web.musicfestival;

import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.web.EntityCreator;
import com.kasakad.fileio.kasakaidfileio.web.MappedDTO;
import com.kasakad.fileio.kasakaidfileio.web.validation.TwoThousandTenConstraint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@TwoThousandTenConstraint
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
    private final MusicFestivalEntityType dtype;

    public EntityCreator<MusicFestivalRequestDTO, ? extends MusicFestival> getCreator() {
        return this.dtype.getCreator();
    }
}
