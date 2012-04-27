package com.noi.utility.spring.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class BLServiceException extends Exception implements Serializable {
	private Collection<BLMessage> messages;

	
	
	public BLServiceException() {
		super();
		initModel();
	}

	public BLServiceException(String message, Throwable cause) {
		super(message, cause);
		initModel();
	}

	public BLServiceException(String message) {
		super(message);
		initModel();
	}

	public BLServiceException(Throwable cause) {
		super(cause);
		initModel();
	}

	private void initModel()
	{
		this.messages = new ArrayList<BLMessage>();
	}
	
	/**
	 * @return Returns the messages.
	 */
	public Collection<BLMessage> getMessages() {
		return messages;
	}

	/**
	 * @param messages The messages to set.
	 */
	public void setMessages(Collection<BLMessage> messages) {
		this.messages = messages;
	}
	
	
	
	
}
