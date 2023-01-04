package com.victor.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.vendas.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
