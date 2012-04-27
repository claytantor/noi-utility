package com.noi.utility.hibernate;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

public class DetachedPojoFactory {
	
	private static final int DEFAULT_DEPTH=4;
	
	static Logger logger = Logger.getLogger(DetachedPojoFactory.class);
	
	/**
	 * needs to support collections recursively, default depth
	 * 
	 * @param pojo
	 * @param propertyNames
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public static Object detachPojoFromSession(Object pojo) 
		throws ClassNotFoundException, 
		InstantiationException,
		IllegalAccessException,
		NoSuchMethodException,
		InvocationTargetException,
		Exception
	{
		Object newPojoObject= detachPojoFromSessionDepth(pojo, 0, DEFAULT_DEPTH);
		//return it
		return newPojoObject;
	}
	
	public static Object detachPojoFromSessionDepth(Object pojo, int depth, int maxdepth) 
		throws ClassNotFoundException, 
		InstantiationException,
		IllegalAccessException,
		NoSuchMethodException,
		InvocationTargetException,
		Exception
	{
		DetachablePojo newPojoObject = null;
		//use reflection to instantiate a new version		
		if(pojo instanceof DetachablePojo)
		{
			DetachablePojo dpojo = (DetachablePojo)pojo;
			String implname = pojo.getClass().getName();
			
			//dollar sign denotes proxy
			if(pojo.getClass().getName().indexOf("$") != -1)
			{
				implname = pojo.getClass().getName().substring(
					0, pojo.getClass().getName().indexOf("$"));
			}
			
			logger.debug("trying to instance class:"+implname);
			Class pojoClass = Class.forName(implname);
			
			
			newPojoObject = (DetachablePojo)pojoClass.newInstance();
			
			
			//copy the properties 
			for (String property : newPojoObject.getPropertyNames()) {
					logger.debug(newPojoObject.getClass().getName()+"."+property);
					
					Object value = null;
					
					try {
						value = PropertyUtils.getSimpleProperty(pojo, property);
					} catch (IllegalAccessException e) {
						logger.debug("trying to get propety:"+property,e);
					} catch (InvocationTargetException e) {
						logger.debug("trying to get propety:"+property,e);
					} catch (NoSuchMethodException e) {
						logger.debug("trying to get propety:"+property,e);
					}
					
					
					
					if((value != null) && value instanceof List  && (depth<maxdepth))
					{
						logger.debug("instanceof List");
						//need to do more work for this 
						Collection c = (Collection)value;
						List newlist = new ArrayList();
						for (Object cobject : c) {
							if(cobject instanceof DetachablePojo)
							{
								Object detachedCObj = detachPojoFromSessionDepth(cobject,depth++,maxdepth);
								newlist.add(detachedCObj);
							}
						}
						PropertyUtils.setProperty(newPojoObject, property, newlist);
					}
					else if((value != null) && value instanceof Set  && (depth<maxdepth))
					{
						logger.debug("instanceof Set");
						//need to do more work for this 
						Collection c = (Collection)value;
						Set newlist = new HashSet();
						for (Object cobject : c) {
							if(cobject instanceof DetachablePojo)
							{
								Object detachedCObj = detachPojoFromSessionDepth(cobject,depth++,maxdepth);
								newlist.add(detachedCObj);
							}
						}
						PropertyUtils.setProperty(newPojoObject, property, newlist);
					}					
					else if((value != null) && value instanceof DetachablePojo)
					{
						logger.debug("instanceof DetachablePojo");
						Object detachedValue = detachPojoFromSessionDepth(value,depth++,maxdepth);
						PropertyUtils.setProperty(newPojoObject, property, detachedValue); 
					}
					//java.sql.Timestamp
					else if(value != null && value instanceof java.sql.Timestamp)
					{	
						java.sql.Timestamp ts = (java.sql.Timestamp)value;
						Date d = new Date(ts.getTime());
						logger.debug("simple property name:"+property+" value type:"+value.getClass().getName()+" value:"+value.toString());
						PropertyUtils.setProperty(newPojoObject, property, d); 
					}
					else if(value != null)
					{						
						logger.debug("simple property name:"+property+" value type:"+value.getClass().getName()+" value:"+value.toString());
						PropertyUtils.setProperty(newPojoObject, property, value); 
					}
	
			}
		}
		else
			throw new Exception("pojo is not an instance of DetachablePojo"); 
		
		//return it
		return newPojoObject;
	}
	
	
}
