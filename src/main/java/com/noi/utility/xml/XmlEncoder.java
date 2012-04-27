package com.noi.utility.xml;

import com.noi.utility.string.StringUtils;


public class XmlEncoder {
	
private static XmlEncoder _instance;
	
	private XmlEncoder()
	{
		
	}
	
	public static XmlEncoder getInstance() 
	{
		if(_instance == null)
			_instance = new XmlEncoder();
		return _instance;
			
	}
	
	public String encodeString(String preEncoding)
	{
		if(StringUtils.isNotEmpty(preEncoding))
			return preEncoding.replace("&","&amp;")
				.replace("<","&lt;")
				.replace(">","&gt;")
				.replace("\'","&apos;")
				.replace("\"","&quot;");
		else
			return "";
	
	}
	
	public String decodeString(String preEncoding)
	{
		if(StringUtils.isNotEmpty(preEncoding))
			return preEncoding.replace("&amp;","&")
				.replace("&lt;","<")
				.replace("&gt;",">")
				.replace("&apos;","\'")
				.replace("&quot;","\"");
		else
			return "";
	
	}
	
	

}
