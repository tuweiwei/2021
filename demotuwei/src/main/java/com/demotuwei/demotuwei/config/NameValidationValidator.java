package com.demotuwei.demotuwei.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidationValidator implements ConstraintValidator<NameValid, String> {
    @Override
    public void initialize(NameValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if ("steven".equalsIgnoreCase(value)) {
            return true;
        }
        String defaultConstraintMessageTemplate = constraintValidatorContext.getDefaultConstraintMessageTemplate();
        System.out.println("default message :" + defaultConstraintMessageTemplate);
        //禁用默认提示信息
        //context.disableDefaultConstraintViolation();
        //设置提示语
        //context.buildConstraintViolationWithTemplate("can not contains blank").addConstraintViolation();
        return false;
    }
}
