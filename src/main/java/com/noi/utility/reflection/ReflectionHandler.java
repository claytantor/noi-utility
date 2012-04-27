/*
 * ReflectionHandler.java
 *
 * Created on November 20, 2001, 2:50 PM
 */

package com.noi.utility.reflection;
import java.lang.reflect.Method;

/**
 *
 * @author  clay
 * @version 
 */
public class ReflectionHandler {
    
    private Class _class;

    /** Creates new ReflectionHandler */
    public ReflectionHandler(Class c) {
        _class = c;
    }
    
    public Method findMethod(String methodName, String[] parameterNames, String returnType)
    {
        Method match = null;
        Method[] methods = _class.getDeclaredMethods();
        int numMethods = methods.length;
        for (int i = 0; i < numMethods; i++) {
            Method t_method = methods[i];
             if(t_method.getName().compareTo(methodName)==0)
            {
                System.out.println("method was found");
                boolean isRType = isReturnType(t_method, returnType);
                boolean isPType = isParameterTypes(t_method, parameterNames);
                    
                if(isRType && isPType)
                {
                    match = t_method;
                    System.out.println("found with parameters");
                }
                else
                {
                    System.out.println("DID NOT MATCH FULLY return match:"+isRType+" parameter match:"+isPType);
                }
            }
            
        }
        
        return match;
       
    }
    
    private boolean isReturnType(Method _method, String _type)
    {
        Class rtype = _method.getReturnType();
        if (rtype.getName().compareTo(_type)==0)
            return true;        
        return false;
    }
    
    private boolean isParameterTypes(Method _method, String[] _types)
    {
        boolean match = false;
        int matchcount = 0;
        int numParameters=0;
        int numTypes=0;

        Class[] parameters = _method.getParameterTypes(); 
        if(parameters != null)
        {
            numParameters = parameters.length;
        }
        if(_types != null)
        {
            numTypes = _types.length;
        }
        
        if (numParameters != numTypes)
            match=false;
        else
        {
            for (int i = 0; i < numParameters; i++) {
                Class parameter = parameters[i];
                if(parameter.getName().compareTo(_types[i])==0)
                    matchcount++;
            }
            if(numParameters == matchcount)
                match=true;
        }
        return match;
    }
    
    public void printClass()
    {
        System.out.println("*****************CLASS REFLECTION HANDLER*********************");
        System.out.println("CLASS NAME:"+_class.getName());
        Method[] methods = _class.getDeclaredMethods();
        int numMethods = methods.length;
        for (int j = 0; j < numMethods; j++) {
            Method t_method = methods[j];
            System.out.println("METHOD:"+getMethodString(t_method));
            getMethodString(t_method); 
        }
        System.out.println("*****************END REFLECTION HANDLER*********************");
        
    }
    
    private String getMethodString(Method _method)
    {
        String smethod = "returns:"+getMethodReturnString(_method)+" name:"+_method.getName()+" parameters:"+getMethodParametersString(_method);
        return smethod;
    }
    
    private String getMethodReturnString(Method _method)
    {
        String sreturns = null;
        Class returnClass = _method.getReturnType();
        return returnClass.getName();
                
    }
    
    private String getMethodParametersString(Method _method)
    {
        String sp = null;
        Class[] parameters = _method.getParameterTypes();
        int numParameters = parameters.length;
        if (numParameters>0)
            sp = "\n";
        else
            sp="none";
        for (int i = 0; i < numParameters; i++) {
            Class parameter = parameters[i];
            sp = sp+parameter.getName()+"\n"; 
        }
        return sp;
        
    }
        

}
