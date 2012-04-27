package com.noi.utility.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class WebMessages implements Serializable{
	private Collection<String> messages;
	
	

	public WebMessages() {
		super();
		this.messages = new ArrayList<String>();
	}

	/**
	 * @return Returns the messages.
	 */
	public Collection<String> getMessages() {
		return messages;
	}

	/**
	 * @param messages The messages to set.
	 */
	public void setMessages(Collection<String> messages) {
		this.messages = messages;
	}
	
}
