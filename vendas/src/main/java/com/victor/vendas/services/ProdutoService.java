package com.victor.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.victor.vendas.domain.Categoria;
import com.victor.vendas.domain.Produto;
import com.victor.vendas.repositories.CategoriaRepository;
import com.victor.vendas.repositories.ProdutoRepository;
import com.victor.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto findById(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objecto não encontrado! Id: " + id));
	}
	
	public Page<Produto> search(String nome, List<Integer> idsCategorias, Integer page, Integer line, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, line, Direction.valueOf(direction), orderBy); 
		List<Categoria> categorias = categoriaRepository.findAllById(idsCategorias);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	} 
}
