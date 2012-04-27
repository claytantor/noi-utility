package com.noi.utility.spring;

import groovy.lang.GroovyObject;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scripting.groovy.GroovyObjectCustomizer;

public class ScriptletCustomizer implements GroovyObjectCustomizer, ApplicationContextAware {
    String[] bindingVars = null;
    ApplicationContext applicationContext = null;

    public void customize(GroovyObject groovyObject) {
        groovyObject.setProperty("springAppContext", applicationContext);

        for(String bindingVar : bindingVars) {
            groovyObject.setProperty(bindingVar, applicationContext.getBean(bindingVar));
        }
    }

    public void setBindingVars(String[] bindingVars) {
        this.bindingVars = bindingVars;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}