package com.ninjaone.backendinterviewproject.catalog.domain.enums.pattern;

import com.ninjaone.backendinterviewproject.catalog.domain.enums.CatalogCompatibilityEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompatibilityEnumValidator implements ConstraintValidator<CompatibilityEnumPattern, String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return CatalogCompatibilityEnum.isMember(value);
    }
}
