package com.victor.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.vendas.domain.Categoria;

@Repository 
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	

}
