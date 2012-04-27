package com.noi.utility.web.command.xml;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.noi.utility.date.DateUtils;
import com.noi.utility.reflection.ReflectionUtils;

public class DefaultActionPojoFactory implements ActionPojoFactory {
	
	static Logger logger = Logger.getLogger(XmlActionDelegate.class);
	
	@Override
	public Object makePojoFromNoun(Noun n, Map pojoFactoryMap)
	{
		return makeBasicPropertiesFromNoun( n, pojoFactoryMap);
	}
	
	protected Object makeBasicPropertiesFromNoun(Noun n, Map pojoFactoryMap)
	{
		Object pojo = null; 
		
		try {
			pojo = pojoFactoryMap.get(
						n.getName()).getClass()
						.newInstance();
			
			for (Property eprop : n.getElementProperties().getProperties()) {
				logger.debug("property name:"+eprop.getName()+" value:"+eprop.getValue());
				
				//need to convert dates?
				Object prop = ReflectionUtils.getProperty(eprop.getName(),pojo);
				String[] parts = eprop.getName().split("\\x2e");
				if(parts.length==1)
				{
					if(prop instanceof Date)
						BeanUtils.setProperty(pojo, eprop.getName(), 
								DateUtils.stringToDate(eprop.getValue(), DateUtils.PATTERN_DATE_STRING) );					
					else
						BeanUtils.setProperty(pojo, eprop.getName(), eprop.getValue());
				}
			}
		
		} catch (NoSuchMethodException e) {
			logger.error("NoSuchMethodException", e);
		} catch (InvocationTargetException e) {
			logger.error("InstantiationException", e);
		} catch (InstantiationException e) {
			logger.error("InstantiationException", e);
		} catch (IllegalAccessException e) {
			logger.error("InstantiationException", e);
		}
		
		return pojo;
	}
	
}
