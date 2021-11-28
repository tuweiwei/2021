package com.demotuwei.demotuwei.config;

import com.demotuwei.demotuwei.annotation.Log;
import com.demotuwei.demotuwei.annotation.LogAspect;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 记录请求日志
 */
@Aspect
@Component
public class SystemLogAspectOper {

    // 基于注解的 切点
    @Pointcut("@annotation(com.demotuwei.demotuwei.annotation.LogAspect)")
    public void logAspect() {
    }

    @Around("logAspect()")
    public Object recordLog(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String methodName = "";

        try {
            // 方法参数
            Object[] args = point.getArgs();
            String reqParam = new ObjectMapper().writeValueAsString(args);

            if (null != request) {
                System.out.println(request.getRequestURI());
            }

            // 获取注解信息
            Signature signature = point.getSignature();
            MethodSignature methodSignature;
            Method method = null;

            if (signature instanceof MethodSignature) {
                methodSignature = (MethodSignature) signature;
                method = methodSignature.getMethod();
                LogAspect logAspect = method.getAnnotation(LogAspect.class);
                if (null != logAspect) {

                }
            }

            if (null != method){
                methodName = method.getName();
            }
        } catch (Exception e) {
        }

        Object result = point.proceed();

        switch (methodName){
            default:
                break;
        }
        return result;
    }
}
