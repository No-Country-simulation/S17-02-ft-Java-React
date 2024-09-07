package com.nocountry.telemedicina.config.customValidate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumDayValidator .class)
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEnumDay {
    String message() default "Invalid day(s) in the list";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
