package com.noi.utility.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class DetachPojoUtil {
	public static void detachPojo(Object source, Object destination, String[] excludeFields)
	{
		if(source != null && destination != null)
		{
			String[] excludeFieldsProxy = appendProxyFields(excludeFields);
	    	BeanUtils.copyProperties(source, destination, excludeFieldsProxy);
		}
	}
	
	private static String[] appendProxyFields(String[] includedFields)
	{
		List<String> strings = new ArrayList<String>();
		for (int i = 0; i < includedFields.length; i++) {
			strings.add(includedFields[i]);
		}
		strings.add("callbacks");
		strings.add("callback");
		strings.add("hibernateLazyInitializer");
		//Object[] objectArray = strings.toArray();
	    String[] array = (String[])strings.toArray(new String[strings.size()]);
		return array;
	}

}
