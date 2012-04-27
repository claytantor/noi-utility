package com.noi.utility.bean;

import java.lang.reflect.InvocationTargetException;

public class BeanUtils {
	public static void copyBean(String[] fieldnames, Object source, Object dest) throws IllegalAccessException, InvocationTargetException {
		for (String name : fieldnames) {
			org.apache.commons.beanutils.BeanUtils.copyProperty(source, name, dest);
		}		
	}
}
