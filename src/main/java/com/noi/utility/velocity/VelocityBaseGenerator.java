package com.noi.utility.velocity;

import java.io.CharArrayWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;


public abstract class VelocityBaseGenerator {
	private VelocityContext vcontext;
	private Template template;	
	private Properties props;
	static Logger logger = Logger.getLogger(VelocityBaseGenerator.class);
	static private Writer out;
	protected static final String VELOCITY_PROPERTIES = "/velocity.properties";
	
	
	public abstract String makeDisplayString();
	public abstract VelocityContext makeContext();
	
	
	protected void initVelocityContextTemplate(Class c, String templateClasspath)
	{
		try{
			this.props = getGeneratorProperties(VELOCITY_PROPERTIES);
 			
			Velocity.init(props);
			
			vcontext = new VelocityContext();			
        	logger.debug("loading template file:"+templateClasspath);
	        template = Velocity.getTemplate(templateClasspath);
		}	
		catch(Exception e)
		{
			logger.debug("problem loading template "+this.getClass().getName());
			e.printStackTrace();
		}		
		
	}
	
	protected void initVelocityContext(Class c, String propertiesClasspath)
	{
		try{
			this.props = getGeneratorProperties(propertiesClasspath);
			logger.debug("velocity properties file:"+propertiesClasspath);
			logger.debug("velocity properties size:"+this.props.size());
			String baseClassName = c.getName().substring(c.getName().lastIndexOf(".")+1);
			logger.debug("base name:"+baseClassName);
 			
			Velocity.init(props);
			
			vcontext = new VelocityContext();			
        	logger.debug("loading template file:"+props.getProperty(baseClassName+".template.file"));
	        template = Velocity.getTemplate(props.getProperty(baseClassName+".template.file"));
		}	
		catch(Exception e)
		{
			logger.debug("problem loading template "+this.getClass().getName());
			e.printStackTrace();
		}		
	}
	
	protected void initVelocityContext(Class c)
	{
		try{
			this.props = getGeneratorProperties(VELOCITY_PROPERTIES);
			
			
			String baseClassName = c.getName().substring(c.getName().lastIndexOf(".")+1);
			logger.debug("base name:"+baseClassName);
 			
			Velocity.init(props);
			
			vcontext = new VelocityContext();			
        	logger.debug("loading template file:"+props.getProperty(baseClassName+".template.file"));
	        template = Velocity.getTemplate(props.getProperty(baseClassName+".template.file"));
		}	
		catch(Exception e)
		{
			logger.debug("problem loading template "+this.getClass().getName());
			e.printStackTrace();
		}		
	}
	
	protected Properties getGeneratorProperties(String propertiesClasspath) throws Exception
	{
		Properties appProps = null;	
		//get properties from classpath
		appProps = new Properties();
		appProps.load(VelocityBaseGenerator.class.getResource(propertiesClasspath).openStream());  
		return appProps;
	}
	
	protected StringBuffer generateFromModelAndTemplate(VelocityContext vcontext, Template template) throws Exception
	{
		
		StringBuffer buf = new StringBuffer();
        CharArrayWriter writer = new CharArrayWriter(); 
        //vcontext.
		if ( template != null)
		    template.merge(vcontext, writer);

		/*
		 *  flush and cleanup
		 */
		
		writer.flush();
		writer.close();		
		buf.append(writer.toCharArray());
			
		return buf;
      
        		
	}	
	/**
	 * @return Returns the template.
	 */
	public Template getTemplate() {
		return template;
	}
	/**
	 * @param template The template to set.
	 */
	public void setTemplate(Template template) {
		this.template = template;
	}
	/**
	 * @return Returns the vcontext.
	 */
	public VelocityContext getVcontext() {
		return vcontext;
	}
	/**
	 * @param vcontext The vcontext to set.
	 */
	public void setVcontext(VelocityContext vcontext) {
		this.vcontext = vcontext;
	}
	
	public static Writer getOut() {
		return out;
	}

	public static void setOut(Writer out) {
		VelocityBaseGenerator.out = out;
	}

	public Properties getVelocityProperties() {
		return props;
	}


	
	
}
