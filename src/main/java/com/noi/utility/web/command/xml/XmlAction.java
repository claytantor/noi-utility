package com.noi.utility.web.command.xml;


public interface XmlAction {
	
	/**
	 * will do the action on the serialized entity
	 * 
	 * @param entity
	 */
	public XmlActionResult doAction(
			Action a, 
			Object pojo) throws XmlActionException;

}
