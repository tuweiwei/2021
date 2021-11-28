package com.demotuwei.demotuwei.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValid {
    String message() default "用户名需满足规则：同时包含数字和字母";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
