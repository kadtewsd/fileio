package com.kasakad.fileio.kasakaidfileio.infrastructure.spring;

import com.kasakad.fileio.kasakaidfileio.exception.InvalidRestResultException;
import com.kasakad.fileio.kasakaidfileio.web.InvalidInformation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidInformation resolveException(Exception ex) {
        return new InvalidInformation(ex.getMessage().trim());
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidInformation handleCustomValidation(MethodArgumentNotValidException ex) {
        return new InvalidInformation(ex.getMessage());
    }

    @ExceptionHandler({ InvalidRestResultException.class })
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<InvalidInformation> handleInvalidItemException(InvalidRestResultException ex) {
        return ex.getInvalidInformation();
    }
}
