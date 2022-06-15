package com.ninjaone.catalog.domain.enums.pattern;

import com.ninjaone.catalog.domain.enums.CatalogCompatibilityEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CompatibilityEnumValidator.class)
@Documented
public @interface CompatibilityEnumPattern{

    String message() default "Invalid Enum";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<CatalogCompatibilityEnum>> enumClass();
}
