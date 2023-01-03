package com.victor.vendas;

import java.io.Serializable;
import java.util.Date;

import com.victor.vendas.domain.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date instante;
	
	@ManyToOne
	Cliente cliente;
	
	public Pedido () {
		
	}
	
	
	
	

}
