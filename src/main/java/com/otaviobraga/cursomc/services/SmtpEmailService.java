package com.otaviobraga.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService{

	@Autowired
	private MailSender mailSender;
	
	@Autowired 
	private JavaMailSender javaMailSender;
	
	public static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando email...");
		mailSender.send(msg);
		LOG.info("Email enviado");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviando email...");
		javaMailSender.send(msg);
		LOG.info("Email enviado");
		
	}

}
