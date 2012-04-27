package com.noi.utility.web.command.xml;

public class Property {
	private String name;
	private String value;
	
	
	public Property(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public Property() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "["+name+"="+value+"]";
	}
	

}
