package com.noi.utility.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class JavamailMailerQueueService implements MailerQueueService {
	
	static Logger logger = Logger.getLogger(JavamailMailerQueueService.class);
	
	private String smtpHostname;	
	private String smtpPort;	
	private String smtpUsername;
	private String smtpPassword;
	private Integer numberThreads;
	private String sslProvider;
	private String securityType;
	
	// create ExecutorService to manage threads
    private ExecutorService threadExecutor = Executors.newFixedThreadPool(10);


	@Override
	public void sendMessage(String fromAddressName,String fromName, String toAddressName,
			String toName, String subject, String body, String mimetype) {
		logger.debug("sending message");
		MailWorker worker = 
			new MailWorker( fromAddressName, fromName,  toAddressName,
				 toName,  subject,  body,  mimetype);
		threadExecutor.execute(worker);
			
	}
	
	public void initService()
	{
		/*queue = new LinkedList<Runnable>();
		threads = new PoolWorker[numberThreads];

        for (int i=0; i<numberThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
        }*/

	}

/*	private class PoolWorker extends Thread {
        public void run() {
            Runnable r;

            while (true) {
            	
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				logger.debug("tick");
                synchronized(queue) {
                	logger.debug("empty1:"+queue.isEmpty());
                    while (queue.isEmpty()) {
                    	logger.debug("empty2:"+queue.isEmpty());
                    	
                    	try {
        					Thread.sleep(1000);
        				} catch (InterruptedException e1) {
        					e1.printStackTrace();
        				}
                    	
                        try
                        {
                            queue.wait();
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                    }

                    r = (Runnable) queue.removeFirst();
                }

                // If we don't catch RuntimeException, 
                // the pool could leak threads
                try {
                    r.run();
                }
                catch (RuntimeException e) {
                    logger.error("problem running thread", e);
                }
            }
        }
    }*/
	
	private class MailWorker implements Runnable
	{
		private String fromAddressName;
		private String fromName;
		private String toAddressName;
		private String toName;
		private String subject;
		private String body;
		private String mimetype;
		
		public MailWorker(String fromAddressName,String fromName, String toAddressName,
				String toName, String subject, String body, String mimetype) {
			super();
			this.fromAddressName = fromAddressName;
			this.fromName = fromName;
			this.toAddressName = toAddressName;
			this.toName = toName;
			this.subject = subject;
			this.body = body;
			this.mimetype = mimetype;
		}



		@Override
		public void run() {
			try {
				logger.debug("running");
				Properties props = new Properties();
				
				if(Boolean.getBoolean(sslProvider))
					java.security.Security
					.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
				
				Authenticator auth = null;
				if(securityType.equalsIgnoreCase("tls"))
				{
					props.put("mail.smtp.starttls.enable","true");
					auth = new SMTPAuthenticator();
					
					props.put("mail.smtp.auth", "true");
				}
				
				props.put("mail.smtp.host", smtpHostname);
				props.put("mail.smtp.port", smtpPort);

				// fill props with any information
				Session session = Session.getInstance(props, auth);
				session.setDebug(true);
				MimeMessage message = new MimeMessage(session);
								
				message.setContent(body, mimetype);
				
				message.setSubject(subject);

				Address toAddress = 
					new InternetAddress(
							toAddressName, 
							toName);
				
				message.addRecipient(
						javax.mail.Message.RecipientType.TO, 
						toAddress);

				Address fromAddress = new InternetAddress(fromAddressName);
				message.setFrom(fromAddress);
				message.saveChanges(); // implicit with send()

				Transport transport = session.getTransport("smtp");
				transport.connect(smtpHostname, smtpUsername, smtpPassword);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();

			} catch (UnsupportedEncodingException e) {
				logger.error("cannot send email", e);
			} catch (MessagingException e) {
				logger.error("cannot send email", e);
			}
			
		}
		
	}

	/**
	 * SimpleAuthenticator is used to do simple authentication when the SMTP
	 * server requires it.
	 */
	private class SMTPAuthenticator extends javax.mail.Authenticator {
 
		public PasswordAuthentication getPasswordAuthentication() {
			String username = smtpUsername;
			String password = smtpPassword;
			return new PasswordAuthentication(username, password);
		}
	}
	
//------------------- injection setters -------------------------//	
	
	public void setSmtpHostname(String smtpHostname) {
		this.smtpHostname = smtpHostname;
	}

	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public void setNumberThreads(Integer numberThreads) {
		this.numberThreads = numberThreads;
	}

	public void setSslProvider(String sslProvider) {
		this.sslProvider = sslProvider;
	}

	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}


	
	
	
}
