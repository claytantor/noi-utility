package com.noi.utility.spring.service;

import java.io.Serializable;

public class BLMessage implements Serializable {
	
	public static int BL_MESSAGE_SYSTEM_ERROR = 1;
	public static int BL_MESSAGE_ACCOUNT_BAL_LT_CART = 100;
	public static int BL_MESSAGE_ACCOUNT_BAL_EXCEEDS_1 = 110;
	public static int BL_MESSAGE_INVALID_SUBSCR_ID = 120;
	
	
	private Integer id;
	private String messageText;
	
	
	
	public BLMessage(String message, int id) {
		super();
		this.messageText = message;
		this.id = new Integer(id);
	}
	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return Returns the messageText.
	 */
	public String getMessageText() {
		return messageText;
	}
	/**
	 * @param messageText The messageText to set.
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	

}
