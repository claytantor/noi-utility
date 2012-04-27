package com.noi.utility.xml;

import com.noi.utility.string.StringUtils;


public class JsonEncoder {
	
private static JsonEncoder _instance;
	
	private JsonEncoder()
	{
		
	}
	
	public static JsonEncoder getInstance() 
	{
		if(_instance == null)
			_instance = new JsonEncoder();
		return _instance;
			
	}
	
	public String encodeString(String preEncoding)
	{
		if(StringUtils.isNotEmpty(preEncoding))
			return preEncoding
			.replaceAll("\\\\","").replaceAll("\\\'","\'").replace("\n", "");
		else
			return "";
	
	}

}
