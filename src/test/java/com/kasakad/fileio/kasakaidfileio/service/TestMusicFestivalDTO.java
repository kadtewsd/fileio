package com.kasakad.fileio.kasakaidfileio.service;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kasakad.fileio.kasakaidfileio.utility.LocalDateTimeDeserializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
// ファイルの並び順を指定しないとめちゃくちゃな順番でバインドされる。
// また、プロパティ名とヘッダー名が一致していないと、無茶苦茶な値がバインドされる。。
// 例 クラスが、festivalName で ヘッダーが name だと、place と name がひっくり返った。
@JsonPropertyOrder(value = {"id","name","place","event_date"})
public class TestMusicFestivalDTO {

    public TestMusicFestivalDTO(int id, String name, String place, LocalDateTime eventDate) {
        this.name = name;
        this.place = place;
        this.eventDate = eventDate;
        this.id = id;
    }

    private final int id;

    private final String name;

    private final String place;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime eventDate;
}
