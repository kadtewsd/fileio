package com.kasakad.fileio.kasakaidfileio.web.musicfestival;

import com.kasakad.fileio.kasakaidfileio.domain.musicfestival.MusicFestival;
import com.kasakad.fileio.kasakaidfileio.web.EntityCreator;
import lombok.Getter;

@Getter
public enum MusicFestivalEntityType {

    DOMAIN(new DomainMusicFestivalCreator()),
    OVERSEA(new OverseaEntityCreator());

    private final EntityCreator<MusicFestivalRequestDTO, ? extends MusicFestival> creator;
    MusicFestivalEntityType(EntityCreator<MusicFestivalRequestDTO, ? extends MusicFestival> creator) {
        this.creator = creator;
    }
}
