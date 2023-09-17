package com.cayena.backend.services.validations;

import com.cayena.backend.enums.UpdateType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumNamePatternValidator implements ConstraintValidator <EnumNamePattern, UpdateType> {
    private UpdateType[] updateTypes;

    @Override
    public void initialize(EnumNamePattern constraintAnnotation) {
        this.updateTypes = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(UpdateType updateType, ConstraintValidatorContext constraintValidatorContext) {
        return updateType == null|| Arrays.asList(updateTypes).contains(updateType);
    }
}