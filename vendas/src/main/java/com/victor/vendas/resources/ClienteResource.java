package com.victor.vendas.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.victor.vendas.domain.Cliente;
import com.victor.vendas.services.ClienteService;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	ClienteService service;
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
