package com.noi.utility.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.noi.utility.data.NameValue;

public class HeaderUtils {
	
	static Logger logger = Logger.getLogger(HeaderUtils.class);
	
	public static List<NameValue> getHeaders(HttpServletRequest request)
	{
		List<NameValue> headers = new ArrayList<NameValue>();
		Enumeration headerNames = request.getHeaderNames();
	    while(headerNames.hasMoreElements()) {
	    	try {
	    		String name = (String)headerNames.nextElement(); 
				if(name != null)					
					headers.add(new NameValue(name,request.getHeader(name)));
				
			} catch (Exception e) {
				logger.debug("header is null.");
			}
	    }
	    return headers;

	}
	
	public static String getHeaderValue(List<NameValue> headers, String name)
	{
		for (NameValue nameValue : headers) {
			if(nameValue.getName().equalsIgnoreCase(name))
				return nameValue.getValue();
		}
		return null;
	}
	
	
}
