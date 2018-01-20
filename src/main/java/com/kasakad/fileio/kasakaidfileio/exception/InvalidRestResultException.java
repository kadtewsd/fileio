package com.kasakad.fileio.kasakaidfileio.exception;

import com.kasakad.fileio.kasakaidfileio.web.InvalidInformation;

import java.util.List;

public class InvalidRestResultException extends RuntimeException {

    private final List<InvalidInformation> invalidInformation;

    public InvalidRestResultException(List<InvalidInformation> invalidInformation) {
        super();
        this.invalidInformation = invalidInformation;
    }
}