package com.noi.utility.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ReflectionUtils {
	
	private static final Log logger = LogFactory
	.getLog(ReflectionUtils.class);
	
	public static String toBeanString(Object o)
	{
		StringBuffer buf = new StringBuffer();
		buf.append("["+o.getClass().getSimpleName()+"]\n{");
		try {
			Map<String,String> description = BeanUtils.describe(o);
			Iterator<String> keys = description.keySet().iterator();
			while(keys != null && keys.hasNext())
			{
				String key = keys.next();
				String value = String.valueOf(PropertyUtils.getSimpleProperty(o, key));
				buf.append("\n\tproperty:"+key+" value:"+value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		buf.append("\n}");
		return buf.toString();				
	}
	
	public static Collection getPropertyNames(Object o1)
	{
		Collection<String> keys = null;
		try {
			Map description = BeanUtils.describe(o1);
			keys = description.keySet();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return keys;
	}
	
	public static boolean isFieldDifferent(String property, Object o1, Object o2)
	{
		boolean isDifferent = false;
		try {
			String value1 = 
			      PropertyUtils.getSimpleProperty(o1, property).toString();
			String value2 = 
			      PropertyUtils.getSimpleProperty(o2, property).toString();
			
			if(!value1.equals(value2))
				isDifferent = false;				
			
		} catch (Exception e) {
			isDifferent = true;
		}
		
		return isDifferent;
	}
	
	/**
	 * this version will attempt to find is methods for boolean types 
	 * and will treat it as though it is a bean property
	 * @param property
	 * @param o1
	 * @return
	 */
	public static Object getProperty(String propertyName, Object o) throws
		NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Object value = null;
		//get methods for property
		
		//does it have a get or is?
		List<Method> getmethods = getMethodsByPropertyAndPrefix(propertyName, o, "get");
		List<Method> ismethods = getMethodsByPropertyAndPrefix(propertyName, o, "is");
		
		if(getmethods.size() > 0)
		{
			value = 
			      PropertyUtils.getSimpleProperty(o, propertyName);
		}
		else if(ismethods.size() > 0)
		{
			value = 
				isMethodFromPropertyNameAndObject(propertyName, o); 
		}

		return value;
	}
	
	/*public Class getPropertyType(String propertyName, Object o)
	throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Object value = null;
		List<Method> getmethods = getMethodsByPropertyAndPrefix(propertyName, o, "get");
		List<Method> ismethods = getMethodsByPropertyAndPrefix(propertyName, o, "is");
		if(getmethods.size() > 0)
		{
			value = 
			      PropertyUtils.getSimpleProperty(o, propertyName);
		}
		else if(ismethods.size() > 0)
		{
			value = 
				isMethodFromPropertyNameAndObject(propertyName, o); 
			
		}
		
		
		return value.getClass();
	}*/

	/**
	 * this will get all mehods whose names start with the expressed string 
	 * @param name
	 * @return
	 */
	public static Collection<Method> getMethodsByStartsWithName(String name, Object o)
	throws NoSuchMethodException, IllegalAccessException
	{
		Collection<Method> methods = new ArrayList<Method>();
		Class c = o.getClass();
   		java.lang.reflect.Method[] theMethods = c.getMethods();
   		for (int i = 0; i < theMethods.length; i++) 
   		{
	         String methodString = theMethods[i].getName();
	         
	         //is this a method we want to use?
	         if(methodString.startsWith(name))
	         {	
	        	 methods.add(theMethods[i]);
		         Class[] parameterTypes = theMethods[i].getParameterTypes();   	
	         }
         
   		}		
		return methods;
	}
	
	/**
	 * this will get all mehods whose names start with the expressed string 
	 * @param name
	 * @return
	 */
	public static List<Method> getMethodsByPropertyAndPrefix(String propertyName, Object o, String prefix)
	throws NoSuchMethodException, IllegalAccessException
	{
		if(o == null)
		{
			logger.debug("we have a null object");
			return null;
		}
		else
		{
			List<Method> methods = new ArrayList<Method>();
			Class c = o.getClass();
	   		java.lang.reflect.Method[] theMethods = c.getMethods();
	   		for (int i = 0; i < theMethods.length; i++) 
	   		{
		         String methodName = theMethods[i].getName();
		         
		         //is this a method we want to add?
		         if(methodName.startsWith(prefix) && getMethodNameToPropertyName(methodName, prefix).equals(propertyName))
		         {	
		        	 methods.add(theMethods[i]);   	
		         }
	         
	   		}		
			return methods;
		}
	}
	
	public static String getMethodNameToPropertyName(String methodname, String prefix)
	{
   		String propertyName = methodname.substring(prefix.length(),prefix.length()+1).toLowerCase()+methodname.substring(prefix.length()+1);
   		return propertyName;
	}

	
	
	/**
	 * invoke all methods that have no params
	 * @param name
	 * @return
	 */
	public static  void invokeEmptyMethods(Collection<Method> methods, Object o)
	throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
	{
		for (Method method : methods) {
	         Class[] parameterTypes = method.getParameterTypes();			     
	         

	         if(parameterTypes.length == 0)
	         {
	        	 logger.debug("method invoked:"+method.getName());
	        	 Object[] arguments = new Object[] {};
		         method.invoke(o, arguments);	         
	         }
			
		}

	}

	
	
	/**
	 * source to dest
	 * @param source
	 * @param dest
	 * @throws Exception
	 */
	public static void copyBean(Object source, Object destination, String[] excludeFields) throws Exception
	{
		String[] excludeFieldsProxy = appendProxyFields(excludeFields);
		org.springframework.beans.BeanUtils.copyProperties(source, destination, excludeFieldsProxy);
	}
	
	private static String[] appendProxyFields(String[] includedFields)
	{
		List<String> strings = new ArrayList<String>();
		for (int i = 0; i < includedFields.length; i++) {
			strings.add(includedFields[i]);
		}
	    String[] array = (String[])strings.toArray(new String[strings.size()]);
		return array;
	}
	
	
	/**
	 * source to dest
	 * @param source
	 * @param dest
	 * @throws Exception
	 */
	public static void copyBean(Object source, Object dest) throws Exception
	{
		//get the properties
		//copyProperties(java.lang.Object dest, java.lang.Object orig) 
		PropertyUtils.copyProperties(dest, source); 
	}
	
	
	
	/**
	 * setMethodFromFieldAndObject will do the setting of the property based on the field name
	 * and value, the same object is returned with the attribute set.
	 * 
	 * @param fieldname
	 * @param fieldvalue
	 * @param bo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static Object setMethodFromPropertyNameAndObject(String propertyName, String fieldvalue, Object bo) 
	   	throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
	   {
	   			Class c = bo.getClass();
	   			//convert it to an expected method name
		   		String methodname = "set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
		   		
		   		java.lang.reflect.Method[] theMethods = c.getMethods();
		   		for (int i = 0; i < theMethods.length; i++) 
		   		{
		      	 
			         String methodString = theMethods[i].getName();
			         //logger.debug("looking for:"+methodname+" found:"+methodString);
			         //is this a method we want to use?
			         if(methodString.equals(methodname))
			         {	         
				         Class[] parameterTypes = theMethods[i].getParameterTypes();			     
				         
				         //only use first to check for type, we
				         // will need to cast the fieldvalue 
				         // according to type
				         Class parameterType = parameterTypes[0];
				         ///logger.debug("****found method:"+methodname+" parameter type:"+parameterType.getName()+" value:"+fieldvalue);
				         
				         if(parameterType.getName().equals("java.lang.String"))
				         {
				         	if(fieldvalue.equals("%"))
				         		fieldvalue = "";
				         	Object[] arguments = new Object[] {fieldvalue};
				         	Class[] nparameterTypes = new Class[] {String.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }			         
				         else if(parameterType.getName().equals("java.lang.Integer"))
				         {
				         	int lval = Integer.parseInt(fieldvalue);
				         	Object[] arguments = new Object[] {new Integer(lval)};				         	
				         	Class[] nparameterTypes = new Class[] {Integer.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }
				         else if(parameterType.getName().equals("java.lang.Long"))
				         {
				         	long lval = Long.parseLong(fieldvalue); 
				         	Object[] arguments = new Object[] {new Long(lval)};
				         	Class[] nparameterTypes = new Class[] {Long.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }
				         else if(parameterType.getName().equals("java.lang.Float"))
				         {
				         	float lval = Float.parseFloat(fieldvalue);
				         	Object[] arguments = new Object[] {new Float(lval)};
				         	Class[] nparameterTypes = new Class[] {Float.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }
				         else if(parameterType.getName().equals("java.lang.Double"))
				         {
				         	double lval = Double.parseDouble(fieldvalue);
				         	Object[] arguments = new Object[] {new Double(lval)};
				         	Class[] nparameterTypes = new Class[] {Double.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }
				         else if(parameterType.getName().equals("java.util.Date"))
				         {
				         	Date d = Calendar.getInstance().getTime();
				         	try{
				         		SimpleDateFormat sdf =
				         			new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
				         		d = sdf.parse(fieldvalue);
				         		
				         	}
				         	catch(Exception e)
							{
				         		logger.debug("cannot parse date",e);
							}
				         	
				         	Object[] arguments = new Object[] { d };
				         		
				         	Class[] nparameterTypes = new Class[] {java.util.Date.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         	
				         }
				         	
			         }
		         
		      }
		      		   	
		   	return bo;
	   }
	
	/**
	 * setMethodFromFieldAndObject will do the setting of the property based on the field name
	 * and value, the same object is returned with the attribute set.
	 * 
	 * @param fieldname
	 * @param fieldvalue
	 * @param bo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static Object getMethodFromPropertyNameAndObject(String propertyName, Object bo) 
	   	throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
	   {
	   			Class c = bo.getClass();
	   			//convert it to an expected method name
		   		String methodname = "get"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
		   		
		   		java.lang.reflect.Method[] theMethods = c.getMethods();
		   		for (int i = 0; i < theMethods.length; i++) 
		   		{
		      	 
			         String methodString = theMethods[i].getName();
			         //logger.debug("looking for:"+methodname+" found:"+methodString);
			         //is this a method we want to use?
			         if(methodString.equals(methodname))
			         {	         
				         Class[] parameterTypes = theMethods[i].getParameterTypes();			     
				         
				         //only use first to check for type, we
				         // will need to cast the fieldvalue 
				         // according to type
				         Class parameterType = parameterTypes[0];
				         //logger.debug("****found method:"+methodname+" parameter type:"+parameterType.getName()+" value:"+fieldvalue);
				         
				         if(parameterType.getName().equals("java.lang.String"))
				         {
				         	if(propertyName.equals("%"))
				         		propertyName = "";
				         	Object[] arguments = new Object[] {propertyName};
				         	Class[] nparameterTypes = new Class[] {String.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }			         
				         else if(parameterType.getName().equals("java.lang.Integer"))
				         {
				         	int lval = Integer.parseInt(propertyName);
				         	Object[] arguments = new Object[] {new Integer(lval)};				         	Class[] nparameterTypes = new Class[] {Integer.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }
				         else if(parameterType.getName().equals("java.lang.Long"))
				         {
				         	long lval = Long.parseLong(propertyName); 
				         	Object[] arguments = new Object[] {new Long(lval)};
				         	Class[] nparameterTypes = new Class[] {Long.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }
				         else if(parameterType.getName().equals("java.lang.Float"))
				         {
				         	float lval = Float.parseFloat(propertyName);
				         	Object[] arguments = new Object[] {new Float(lval)};
				         	Class[] nparameterTypes = new Class[] {Float.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }
				         else if(parameterType.getName().equals("java.lang.Double"))
				         {
				         	double lval = Double.parseDouble(propertyName);
				         	Object[] arguments = new Object[] {new Double(lval)};
				         	Class[] nparameterTypes = new Class[] {Double.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }
				         else if(parameterType.getName().equals("java.util.Date"))
				         {
				         	Date d = Calendar.getInstance().getTime();
				         	try{
				         		 d = DateFormat.getDateInstance().parse(propertyName);
				         	}
				         	catch(Exception e)
							{
				         		
							}
				         	
				         	Object[] arguments = new Object[] { d };
				         		
				         	Class[] nparameterTypes = new Class[] {java.util.Date.class};
				         	java.lang.reflect.Method setMethod = c.getMethod(methodname, nparameterTypes);
				         	setMethod.invoke(bo, arguments);
				         }
				         	
			         }
		         
		      }
		      		   	
		   	return bo;
	   }
	/**
	 * setMethodFromFieldAndObject will do the setting of the property based on the field name
	 * and value, the same object is returned with the attribute set.
	 * 
	 * @param fieldname
	 * @param fieldvalue
	 * @param bo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static Object isMethodFromPropertyNameAndObject(String propertyName, Object bo) 
	   	throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
	   {
   			Class c = bo.getClass();
   			//convert it to an expected method name
	   		String methodname = "is"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
	   		
	   		java.lang.reflect.Method[] theMethods = c.getMethods();
	   		for (int i = 0; i < theMethods.length; i++) 
	   		{
	      	 
		         String methodString = theMethods[i].getName();
		         //logger.debug("looking for:"+methodname+" found:"+methodString);
		         //is this a method we want to use?
		         if(methodString.equals(methodname))
		         {	         
		        	 
		        	 Object[] arguments = new Object[] {null};
		        	 Class[] nparameterTypes = new Class[] {String.class};
		        	 java.lang.reflect.Method isMethod = c.getMethod(methodname, nparameterTypes);
			         isMethod.invoke(bo, arguments);
			         
			         // Call the property getter and return the value
			         Object value = invokeMethod(isMethod, bo, new Object[0]);
			         return (value);
			         	
		         }
	         
	   		}
		      		   	
		   	return null;
	   }
	
    /** This just catches and wraps IllegalArgumentException. */
    public static Object invokeMethod(
                        Method method, 
                        Object bean, 
                        Object[] values) 
                            throws
                                IllegalAccessException,
                                InvocationTargetException {
        try {
            
            return method.invoke(bean, values);
        
        } catch (IllegalArgumentException e) {
            
            //log.error("Method invocation failed.", e);
            throw new IllegalArgumentException(
                "Cannot invoke " + method.getDeclaringClass().getName() + "." 
                + method.getName() + " - " + e.getMessage());
            
        }
    }	
}
