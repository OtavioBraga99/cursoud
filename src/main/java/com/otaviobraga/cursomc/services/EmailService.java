package com.otaviobraga.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.otaviobraga.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderCOnfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
