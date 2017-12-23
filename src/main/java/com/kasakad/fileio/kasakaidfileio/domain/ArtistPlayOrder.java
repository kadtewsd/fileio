package com.kasakad.fileio.kasakaidfileio.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ArtistPlayOrder {

    private final Artist artist;

    private final int playOrder;

}
