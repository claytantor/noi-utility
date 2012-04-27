package com.noi.utility.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class DomParser {
	static Logger logger = Logger.getLogger(DomParser.class);
	
	public static Document parseXmlFile(InputStream ios) throws Exception{
		
		
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		
		try {
			
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//parse using builder to get DOM representation of the XML file
			doc = db.parse(ios);
			

		}catch(ParserConfigurationException pce) {
			logger.error("ParserConfigurationException:",pce);
			throw pce;
		}catch(SAXException se) {
			logger.error("SAXException:",se);
			throw se;
		}catch(IOException ioe) {
			logger.error("IOException:",ioe);
			throw ioe;
		}
		
		return doc;
	}

}
