package com.noi.utility.web.command.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementProperties {

	private List<Property> properties = 
		new ArrayList<Property>();

	public List<Property> getProperties() {
		return properties;
	}

	public void addProperty(Property prop) {
		this.properties.add(prop);
	}
	
	public Map<String, String> getPropertiesMap()
	{
		Map<String, String> emap = new HashMap<String,String>();
		for (Property eprop : this.properties) {
			emap.put(eprop.getName(), eprop.getValue());
		}
		return emap;
	}
	
	public void setProperties(List<Property> properties)
	{
		this.properties = properties;
	}
	

}
