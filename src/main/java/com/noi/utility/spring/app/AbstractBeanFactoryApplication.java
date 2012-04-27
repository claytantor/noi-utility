package com.noi.utility.spring.app;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractBeanFactoryApplication {
private static ApplicationContext beanFactory;
	
	
	protected void setUp() throws Exception {
		if(beanFactory == null)
		{
			beanFactory = new ClassPathXmlApplicationContext(getSpringResources());
		}
		
	}
	
	protected static void init(String[] resources)
	{
		if(beanFactory == null)
		{
			beanFactory = new ClassPathXmlApplicationContext(resources);
		}
	}
	
	
	protected abstract String[] getSpringResources();

	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	public static ApplicationContext getApplicationContext() {
		return beanFactory;
	}
}
