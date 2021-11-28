package com.demotuwei.demotuwei.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 需要记录日志的方法加此注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAspect {
    // 描述
    String description() default "";
    // 操作的类型，1、添加 2、修改 3、删除 0、查询
    String actionType() default "query";
}
