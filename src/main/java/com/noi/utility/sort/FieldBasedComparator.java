package com.noi.utility.sort;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;


public class FieldBasedComparator<T> implements Comparator<T> {
	
	static Logger logger = Logger.getLogger(FieldBasedComparator.class);
	
	private String fieldName;
	private Boolean orderAsc;
	
	public FieldBasedComparator(String fieldName, Boolean orderAsc) {
		super();
		this.fieldName = fieldName;
		this.orderAsc = orderAsc;
	}



	@Override
	public int compare(Object o1, Object o2) {
		
		try {
			Comparable f1 = (Comparable)BeanUtils.getProperty(o1, this.fieldName);
			Comparable f2 = (Comparable)BeanUtils.getProperty(o2, this.fieldName);
			
			if(this.orderAsc)
				return f1.compareTo(f2);
			else
				return f2.compareTo(f1);
			
		} catch (IllegalAccessException e) {
			logger.error("cannot compare", e);
		} catch (InvocationTargetException e) {
			logger.error("cannot compare", e);
		} catch (NoSuchMethodException e) {
			logger.error("cannot compare", e);
		}

		return 0;
	}

}
