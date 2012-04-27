package com.noi.utility.properties;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import com.noi.utility.regex.RegularExpressionUtils;



public class PropertiesUtils {

	public static Properties getPropertiesByPrefix(Properties properties, String prefix)
	{
		Properties mappingProperties = new Properties();
		//get form properties
		for (Enumeration e = properties.keys() ; e.hasMoreElements() ;) {
	         //System.out.println(e.nextElement());
			
			String key = e.nextElement().toString();
			if(key.startsWith(prefix))
			{
				mappingProperties.put(key, RegularExpressionUtils.cleanWhitespace(properties.get(key).toString()));
			}
	    }		
		
		return mappingProperties;
	}
	
	public static Properties loadPropertiesFromClasspath(Class loadResourceClass, String classpath) throws Exception
	{
		//get the build number
    	Properties aprops = new Properties();
    	InputStream ios = loadResourceClass.getResource(classpath).openStream();
    	aprops.load(ios);  
    	return aprops;
		
	}

}
