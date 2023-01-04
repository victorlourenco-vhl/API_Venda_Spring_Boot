package com.victor.vendas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.vendas.domain.Categoria;
import com.victor.vendas.repositories.CategoriaRepository;
import com.victor.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Objeto com id " + id + " não encontrado na base de dados, Tipo: " + Categoria.class.getName()));
	}

}
