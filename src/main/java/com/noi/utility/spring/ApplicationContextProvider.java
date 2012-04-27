package com.noi.utility.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext ctx = null;
    public static ApplicationContext getApplicationContext() {         
        return ctx;    
    }
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        // Assign the ApplicationContext into a static method
        this.ctx = ctx;
    }
    
    public Object getBean(String beanName) {
    	return ctx.getBean(beanName);
    }
}