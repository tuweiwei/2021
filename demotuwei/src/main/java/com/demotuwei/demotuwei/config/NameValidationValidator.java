package com.demotuwei.demotuwei.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NameValidationValidator implements ConstraintValidator<NameValid, String> {
    private static Pattern pattern = Pattern.compile("\\w+\\d+");

    @Override
    public void initialize(NameValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (pattern.matcher(value).matches()) {
            return true;
        }
        String defaultConstraintMessage = constraintValidatorContext.getDefaultConstraintMessageTemplate();
        //禁用默认提示信息
        //context.disableDefaultConstraintViolation();
        //设置提示语
        //context.buildConstraintViolationWithTemplate("can not contains blank").addConstraintViolation();
        return false;
    }
}
