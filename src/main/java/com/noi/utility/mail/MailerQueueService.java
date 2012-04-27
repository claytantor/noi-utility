package com.noi.utility.mail;

public interface MailerQueueService {
	
	public void sendMessage(String fromAddressName,String fromName, String toAddressName,
					String toName, String subject, String body, String mimetype);
	
}
