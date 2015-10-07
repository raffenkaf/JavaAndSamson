package com.samson.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	
	@Autowired
    private MailSender mailSender;
	
	private JavaMailSenderImpl javaMailSenderImpl = 
			new JavaMailSenderImpl();
	
    public void sendSimpleMail(String to, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
      
    public void sendMailWithPrintScreen(String to, String subject, 
    		String body, File image) throws MessagingException
    {
    	MimeMessage message = javaMailSenderImpl.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message);
    	helper.setTo(to);
    	helper.setText(body);
    	helper.setSubject(subject);
    	helper.addAttachment("PrintScreen.jpg", image);
    	javaMailSenderImpl.send(message);
    }
}
