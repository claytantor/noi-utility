package com.noi.utility.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateSchemaExport {
	
	
	
	static Logger logger = Logger.getLogger(HibernateSchemaExport.class);
	
	public static void schemaExport(String configurationClasspath)
	throws Exception
	{
		
		Configuration cfg = new Configuration().configure(configurationClasspath);
		SchemaExport schemaExport = new SchemaExport(cfg); 
		schemaExport.create(false, true);
	}

}
