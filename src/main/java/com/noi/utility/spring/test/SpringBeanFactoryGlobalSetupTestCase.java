package com.noi.utility.spring.test;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanFactoryGlobalSetupTestCase extends TestCase {
private static ApplicationContext beanFactory;
	
	
	public SpringBeanFactoryGlobalSetupTestCase(String arg0) {
		super(arg0);
	}


	protected static void init(String[] resources)
	{
		if(beanFactory == null)
		{
			beanFactory = new ClassPathXmlApplicationContext(resources);
		}
	}
	

	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	public static ApplicationContext getApplicationContext() {
		return beanFactory;
	}
}
