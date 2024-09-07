package com.nocountry.telemedicina.config.customValidate;


import com.nocountry.telemedicina.models.enums.EnumDay;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EnumDayValidator implements ConstraintValidator<ValidEnumDay,List<EnumDay>> {
    @Override
    public boolean isValid(List<EnumDay> days, ConstraintValidatorContext context) {
        if (days == null) {
            return false;
        }

        for (EnumDay day : days) {
            if (day == null) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void initialize(ValidEnumDay constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
