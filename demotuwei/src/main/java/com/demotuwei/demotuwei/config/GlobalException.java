package com.demotuwei.demotuwei.config;

import com.demotuwei.demotuwei.enums.ResultCodeEnum;
import com.demotuwei.demotuwei.uitl.ResultVo;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResultVo methodArgumentNotValidException(MethodArgumentNotValidException e) throws NoSuchFieldException {
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        Class<?> parameterType = e.getParameter().getParameterType();
        String fieldName = e.getBindingResult().getFieldError().getField();
        Field field = parameterType.getDeclaredField(fieldName);

        // 获取field上的  错误自定义注解
//        ExceptionCode annotation = field.getAnnotation(ExceptionCode.class);

//        if (annotation != null) {
//            return new ResultVO(annotation.value(), annotation.message() + defaultMessage, null);
//        }

        // 返回统一错误码
        return new ResultVo(ResultCodeEnum.FAILED, defaultMessage);
    }
}
