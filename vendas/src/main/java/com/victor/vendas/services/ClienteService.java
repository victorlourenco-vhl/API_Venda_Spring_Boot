package com.victor.vendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.vendas.domain.Cliente;
import com.victor.vendas.repositories.ClienteRepository;
import com.victor.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repo;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Cliente com id: " + id + " não encontrado no banco de dados." + Cliente.class.getName()));
	}

}
