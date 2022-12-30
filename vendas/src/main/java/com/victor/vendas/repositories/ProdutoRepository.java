package com.victor.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.vendas.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
