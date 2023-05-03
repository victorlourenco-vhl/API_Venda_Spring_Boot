package com.victor.vendas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.vendas.domain.Email;
import com.victor.vendas.services.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping(value = "/email")
public class EmailResource {
	
	@Autowired
	private EmailService service;
	
	@PostMapping
	private void enviarEmail(@RequestBody Email email) throws MessagingException {
		service.enviarEmail(email);
	}
	
}
