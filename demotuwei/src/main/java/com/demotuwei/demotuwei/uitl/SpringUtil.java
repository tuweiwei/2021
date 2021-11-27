package com.demotuwei.demotuwei.uitl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext cx;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.cx = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return cx;
    }

    public static Object getBeanById(String id) {
        return cx.getBean(id);
    }

    public static <T> T getBean(String id, Class<T> clazz) {
        return getApplicationContext().getBean(id, clazz);
    }
}
