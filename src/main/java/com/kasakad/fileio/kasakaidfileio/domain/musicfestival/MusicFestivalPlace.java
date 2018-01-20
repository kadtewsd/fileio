package com.kasakad.fileio.kasakaidfileio.domain.musicfestival;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString
public class MusicFestivalPlace {

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String place;
}
