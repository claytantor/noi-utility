package com.noi.utility.html;

import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;

public class HtmlModelFactory {
	
	static Logger logger = Logger.getLogger(HtmlModelFactory.class);
	
	
	/**
	 * simple method will extract basic table data from an html file
	 * 
	 * @param body
	 * @return
	 */
	public static HtmlBody makeBody(InputStream ios) throws Exception
	{
		HtmlBody body = null;
		
		try {
			
			//ByteArrayInputStream bais = new ByteArrayInputStream(bodyString.getBytes());
			
			Digester digester = new Digester();
			digester.setValidating( false );
			digester.addObjectCreate( "html/body", HtmlBody.class );
			digester.addObjectCreate( "html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody", HtmlTable.class );
			digester.addSetNext("html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody", "addTable");
			
			digester.addObjectCreate("html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr", HtmlRow.class);
			digester.addSetNext("html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr", "addRow");

			digester.addObjectCreate("html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td", HtmlColumn.class);
			digester.addSetProperties( "html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td", "class", "tagClass" );
			digester.addSetProperties( "html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td", "header", "header" );
			digester.addBeanPropertySetter( "html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td", "cellBody" );
			digester.addSetNext("html/body/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td", "addColumn");
			
			
			
			body = (HtmlBody)digester.parse( ios );
			  	          
       
	          
	     } catch( Exception exc ) {
	        logger.debug("problem parsing",exc);
	        throw exc;
	     } 
		
		
		return body;
	}

	
	
}
