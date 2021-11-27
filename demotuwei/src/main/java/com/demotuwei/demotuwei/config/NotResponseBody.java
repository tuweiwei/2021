package com.demotuwei.demotuwei.config;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 不需要統一响应时使用
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface NotResponseBody {

}
