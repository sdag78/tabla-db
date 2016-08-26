package com.stevengiblin.spring.taleemdb.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/*
 * application password: 
 * 		https://security.google.com/settings/security/apppasswords
 * 
 * 2-step verification:
 * 		https://support.google.com/accounts/answer/185839
 */

@Component
@Primary
public class SmtpMailSender implements MailSender {

	private static final Log log = LogFactory.getLog(SmtpMailSender.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;	
	}
	
	@Override
	@Async
	public void send(String to, String subject, String body) throws MessagingException {
		log.info("Sending REAL mail to " + to);
		log.info("Subject: " + subject);
		log.info("Body: " + body);
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message, true);
		
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);
		
		javaMailSender.send(message);
	
	}
	
}
