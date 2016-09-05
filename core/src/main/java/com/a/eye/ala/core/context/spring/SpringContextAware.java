package com.a.eye.ala.core.context.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by wusheng on 16/9/5.
 */
public class SpringContextAware implements ApplicationContextAware{
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextAware.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext(){
        return SpringContextAware.applicationContext;
    }
}
