package com.demotuwei.demotuwei.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValid {
    String message() default "不是合法的名字, 需满足规则xxxx";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
