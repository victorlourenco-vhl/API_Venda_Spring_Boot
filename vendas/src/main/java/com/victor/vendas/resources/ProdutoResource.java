package com.victor.vendas.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victor.vendas.domain.Produto;
import com.victor.vendas.dto.ProdutoDTO;
import com.victor.vendas.resources.utils.URL;
import com.victor.vendas.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	ProdutoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> findById(@PathVariable Integer id){
		Produto produto = service.findById(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categoria", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue="0") Integer page,
			@RequestParam(value = "lines", defaultValue = "12") Integer lines,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy){
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> idsCategoria = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, idsCategoria, page, lines, direction, orderBy);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
		
	}
	
}




