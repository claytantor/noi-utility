package com.noi.utility.bean;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BeanSerializer {
	
	/*
	 * This class will encode/serialize Beans into XML
	 * and decode xml back into Objects
	 */

	private BeanSerializer() {
	}
	
	/*
	 * Make sure the @param obj follows the JavaBean pattern correctly
	 */
	public static synchronized String encodeObject(Object obj) {
		
		if(obj == null) return null;
		
		//without this, ClassNotFoundException gets thrown for some reason
		Thread.currentThread().setContextClassLoader(BeanSerializer.class.getClassLoader());
		
		ByteArrayOutputStream io = new ByteArrayOutputStream();
		
		XMLEncoder e = new XMLEncoder(
					new BufferedOutputStream(io));   
		e.writeObject(obj);        
		e.close();
		
		return new String(io.toByteArray());
	}
	
	public static synchronized Object decodeObject(String encStr) {
		
		if(encStr == null) return null;
		
		//without this, ClassNotFoundException gets thrown for some reason
		Thread.currentThread().setContextClassLoader(BeanSerializer.class.getClassLoader());
		
		XMLDecoder d = new XMLDecoder(
	            new BufferedInputStream(
	                new ByteArrayInputStream(
	                		encStr.getBytes())));
		
		Object result = d.readObject();
		d.close();
		
		return result;
	}
}
