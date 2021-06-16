package com.userregistration.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Email {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String receiver, String token) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("akilrainadesigan@gmail.com");
		message.setTo(receiver);
		message.setText("Please click on the below link to verify : \n http://fundooUser/userregistration/verify/"+token);
		message.setSubject("Mail Validation");
		
		mailSender.send(message);
	}
}
