package com.noi.utility.spring;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * considered obsolete use SpringBeanFactory instead
 * 
 * @author clay
 *
 */
public class BeanFactoryLoader {
	
	private static Log log = LogFactory.getLog(BeanFactoryLoader.class);
    private static ClassPathXmlApplicationContext beanFactory;

    static {

        try {
            BeanFactoryLocator beanFactoryLocator = SingletonBeanFactoryLocator
                    .getInstance("classpath:applicationContext.xml");
            beanFactory = (ClassPathXmlApplicationContext) beanFactoryLocator
                    .useBeanFactory("SpringResources").getFactory();


        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("Error Loading Bean Factory", e);
        }

    }
    
    private BeanFactoryLoader()
    {
    	
    }
    
    public static Map getBeansOfType(Class classType) {
        return BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory,
                classType, true, true);
    }

    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    public static Object getBean(String name) {
        return beanFactory.getBean(name);
    }
    
    public static ApplicationContext getFactory()
    {
    	return beanFactory;
    }

}
