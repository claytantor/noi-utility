package com.noi.utility.web.command.xml;

import java.util.ArrayList;
import java.util.List;

import com.noi.utility.data.NameValue;



public class ActionResult {
	
	private String actionStatus;
	
	private List<NameValue> properties = 
		new ArrayList<NameValue>();
	
	public String getActionStatus() {
		return actionStatus;
	}
	
	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public List<NameValue> getProperties() {
		return properties;
	}

	public void setProperties(List<NameValue> properties) {
		this.properties = properties;
	}
	public NameValue getPropertyByName(String name)
	{
		for (NameValue property : this.properties) {
			if(property.getName().equals(name))
			{
				return property;
			}
		}
		return null;
	}
}
