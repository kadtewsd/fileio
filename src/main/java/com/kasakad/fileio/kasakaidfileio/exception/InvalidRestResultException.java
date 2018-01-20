package com.kasakad.fileio.kasakaidfileio.exception;

import com.kasakad.fileio.kasakaidfileio.web.InvalidInformation;
import lombok.Getter;

import java.util.List;

public class InvalidRestResultException extends RuntimeException {

    @Getter
    private final List<InvalidInformation> invalidInformation;

    public InvalidRestResultException(List<InvalidInformation> invalidInformation) {
        super();
        this.invalidInformation = invalidInformation;
    }
}