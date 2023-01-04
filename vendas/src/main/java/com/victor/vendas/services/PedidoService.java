package com.victor.vendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.vendas.domain.Pedido;
import com.victor.vendas.repositories.PedidoRepository;
import com.victor.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository repo;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Pedido com id: " + id + " não encontrado na base de dados. " + Pedido.class.getName()));
	}
	
}
