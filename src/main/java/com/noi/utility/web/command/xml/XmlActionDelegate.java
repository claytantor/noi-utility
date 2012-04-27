package com.noi.utility.web.command.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;

import com.noi.utility.io.InputOutputUtils;

public class XmlActionDelegate 
{	
	static Logger logger = Logger.getLogger(XmlActionDelegate.class);
	
	private Map<String,Object> actionLocatorMap;
	private Map<String,Object> pojoFactoryMap;	
	private ActionPojoFactory pojoFactory;
	
	public XmlActionDelegate(
			Map<String,Object> actionLocatorMap, 
			Map<String,Object> pojoFactoryMap,
			ActionPojoFactory pojoFactory) {
		super();
		this.actionLocatorMap = actionLocatorMap;
		this.pojoFactoryMap = pojoFactoryMap;
		this.pojoFactory =pojoFactory;
	}
	
	/**
	 * 
	 * first digest the xml so we can act on the command
		if I was a better programmer I could figure out how
		to digest directly into the command model, but I cant
		think about that right now. basically if I try to have 
		the perfect architecture on the first pass I am spending
		to much time. a *good* architecture that lets us refactor 
		later is a more agile approach
	 * 
	 * @param ios
	 * @return
	 */
	public XmlActionResult delegate(InputStream ios)
	{
		XmlActionResult result = new XmlActionResult();
		
		
		try {
			
			//this debugging transformation should be 
			//removed when the code is stable
			String sio = 
				InputOutputUtils.getInputStringFromStream(ios, "UTF-8");
			
			logger.debug("delegate command:"+sio);
			
			ByteArrayInputStream bais = 
				new ByteArrayInputStream(sio.getBytes());	
			
			Action actionModel = makeActionModelFromXmlStream(bais);
			
			XmlAction actionBean = 
				(XmlAction)this.actionLocatorMap.get(
						actionModel.getVerb().getName()+
						"_"+actionModel.getNoun().getName());
			
			Object pojo = 
				pojoFactory.makePojoFromNoun(
						actionModel.getNoun(),
						pojoFactoryMap);			
			
			//do the action
			result = actionBean.doAction(
					actionModel, pojo);
			
			
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException", e);
			result.setActionStatus("ERROR");
			
		} catch (XmlActionException e) {
			logger.error("cannot execute action", e);
			result.setActionStatus("ERROR");
			
		}
				
		return result;
	}
	

	/*private Object makePojoFromNoun(Noun n)
	{
		Object pojo = null; 
		
		try {
			pojo = this.pojoFactoryMap.get(
						n.getName()).getClass()
						.newInstance();
			
			for (Property eprop : n.getElementProperties().getProperties()) {
				logger.debug("property name:"+eprop.getName()+" value:"+eprop.getValue());
				
				//need to convert dates?
				Object prop = ReflectionUtils.getProperty(eprop.getName(),pojo);
				if(prop instanceof Date)
					BeanUtils.setProperty(pojo, eprop.getName(), 
							DateUtils.stringToDate(eprop.getValue(), DateUtils.PATTERN_DATE_STRING) );					
				else
					BeanUtils.setProperty(pojo, eprop.getName(), eprop.getValue());
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
	}*/
	
	public Action makeActionModelFromXmlStream(InputStream ios)
	{
		Action action = null;
		try {
		      Digester digester = new Digester();
		      
		      digester.setValidating( false );
		      digester.addObjectCreate( "action", Action.class );
		      
		      //noun
		      digester.addObjectCreate( "action/noun", Noun.class );
		      digester.addBeanPropertySetter( "action/noun/name", "name" );		
		      
		      digester.addObjectCreate( "action/noun/elementProperties", ElementProperties.class );		      		      
		      digester.addObjectCreate( "action/noun/elementProperties/property", Property.class );
		      digester.addBeanPropertySetter( "action/noun/elementProperties/property/name", "name" );
		      digester.addBeanPropertySetter( "action/noun/elementProperties/property/value", "value" );		  
		      digester.addSetNext( "action/noun/elementProperties/property", "addProperty" );
		    
		      digester.addSetNext( "action/noun/elementProperties", "setElementProperties" );
		      digester.addSetNext( "action/noun", "setNoun" );
		      
		      //verb
		      digester.addObjectCreate( "action/verb", Verb.class );
		      digester.addBeanPropertySetter( "action/verb/name", "name" );		
		      
		      digester.addObjectCreate( "action/verb/elementProperties", ElementProperties.class );		      		      
		      digester.addObjectCreate( "action/verb/elementProperties/property", Property.class );
		      digester.addBeanPropertySetter( "action/verb/elementProperties/property/name", "name" );
		      digester.addBeanPropertySetter( "action/verb/elementProperties/property/value", "value" );		  
		      digester.addSetNext( "action/verb/elementProperties/property", "addProperty" );
		    
		      digester.addSetNext( "action/verb/elementProperties", "setElementProperties" );
		      digester.addSetNext( "action/verb", "setVerb" );		      	      
		      
		      //principal 
		      digester.addObjectCreate( "action/principal", Principal.class );
		      digester.addBeanPropertySetter( "action/principal/name", "name" );
		      digester.addBeanPropertySetter( "action/principal/id", "id" );
		      digester.addBeanPropertySetter( "action/principal/token", "token" );		  
		      digester.addSetNext( "action/principal", "setPrincipal" );
	      
		      action = (Action)digester.parse( ios );
		      
		      
	    } catch( Exception exc ) {
	      exc.printStackTrace();
	    }
		    
		return action;
	}

	public void setPojoFactory(ActionPojoFactory pojoFactory) {
		this.pojoFactory = pojoFactory;
	}
	
	
	
	
}
