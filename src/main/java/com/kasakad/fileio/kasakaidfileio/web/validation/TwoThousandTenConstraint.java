package com.kasakad.fileio.kasakaidfileio.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@Target({TYPE, ANNOTATION_TYPE, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy={TwoThousandTenConstraintValidator.class})
public @interface TwoThousandTenConstraint {

    String country() default "country";

    String eventDate() default "eventDate";

    String message() default "開催日は 2010 年以降である必要があります。";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

