package com.kasakad.fileio.kasakaidfileio.web;

import java.util.List;

public abstract class ServiceResult {

    private String message;

    private static final String ERROR_NOT_EXISTS = "エラーは存在しません。";
    protected List<InvalidInformation> invalidInformation;

    public ServiceResult(List<InvalidInformation> invalidInformation) {
        this.invalidInformation = invalidInformation;
    }

    public List<InvalidInformation> fail() {
        return invalidInformation;
    }

    public String errorMessage() {
        if (invalidInformation.isEmpty()) return ERROR_NOT_EXISTS;
        StringBuilder stringBuilder = new StringBuilder();
        invalidInformation.forEach(x -> stringBuilder.append(x.getMessage() + "\n"));
        return stringBuilder.toString().trim();
    }

    public String getMessage() {
        return message;
    }

    public boolean hasError() {
        return !invalidInformation.isEmpty();
    }

    public ServiceResult success(String message) {
        this.message = message;
        return this;
    }
}

