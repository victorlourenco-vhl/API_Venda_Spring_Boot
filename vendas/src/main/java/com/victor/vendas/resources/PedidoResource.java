package com.victor.vendas.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.victor.vendas.domain.Categoria;
import com.victor.vendas.domain.Pedido;
import com.victor.vendas.dto.CategoriaDTO;
import com.victor.vendas.services.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	PedidoService service;
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Pedido> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

}
