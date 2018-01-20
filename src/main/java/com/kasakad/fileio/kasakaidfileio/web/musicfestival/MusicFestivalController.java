package com.kasakad.fileio.kasakaidfileio.web.musicfestival;

import com.kasakad.fileio.kasakaidfileio.domain.service.MusicFestivalService;
import com.kasakad.fileio.kasakaidfileio.domain.service.MusicFestivalServiceResult;
import com.kasakad.fileio.kasakaidfileio.exception.InvalidRestResultException;
import com.kasakad.fileio.kasakaidfileio.web.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/music_festival")
@RequiredArgsConstructor
public class MusicFestivalController {

    private final MusicFestivalService musicFestivalService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResult create(@Validated @RequestBody MusicFestivalRequestDTO musicFestivalDTO) {
        MusicFestivalServiceResult result = musicFestivalService.register(musicFestivalDTO);
        if (result.hasError()) {
            throw new InvalidRestResultException(result.fail());
        }
        return result.success("物販が正常に登録されました。");
    }
}
