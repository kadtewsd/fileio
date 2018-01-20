package com.kasakad.fileio.kasakaidfileio.web.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

@Slf4j
public class TwoThousandTenConstraintValidator implements ConstraintValidator<TwoThousandTenConstraint, Object> {

    private String country;
    private String eventDate;
    private String message;

    private static final LocalDateTime threshold = LocalDateTime.of(2010, 1,1, 0, 0,0 );

    @Override
    public void initialize(TwoThousandTenConstraint constraintAnnotation) {
        this.country = constraintAnnotation.country();
        this.eventDate = constraintAnnotation.eventDate();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        LocalDateTime eventDate = (LocalDateTime) beanWrapper.getPropertyValue(this.eventDate);
        String country = (String) beanWrapper.getPropertyValue(this.country);
        log.info("country is {}", country);
        context.buildConstraintViolationWithTemplate(message);
        return threshold.isEqual(eventDate) || threshold.isBefore(eventDate);
    }
}
