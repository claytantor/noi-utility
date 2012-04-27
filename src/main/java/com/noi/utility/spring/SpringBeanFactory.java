package com.noi.utility.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanFactory {
	private static SpringBeanFactory _instance;
	private BeanFactory beanFactory;
	
	private SpringBeanFactory()
	{
		
	}
	
	public static SpringBeanFactory initialize(String[] configFiles) throws AlreadyInitializedException
	{
		if(_instance == null)
		{
			_instance= new SpringBeanFactory();
			_instance.beanFactory = 
				new ClassPathXmlApplicationContext(configFiles);
			return _instance;
			
		}
		else
			throw new AlreadyInitializedException();
		
		
	}
	
	public static SpringBeanFactory getInstance() throws NotInitializedException
	{
		if(_instance != null)
		{
			return _instance;
		}
		else
			throw new NotInitializedException();
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}


}
