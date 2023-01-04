package com.victor.vendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victor.vendas.domain.Categoria;
import com.victor.vendas.domain.Cidade;
import com.victor.vendas.domain.Cliente;
import com.victor.vendas.domain.Endereco;
import com.victor.vendas.domain.Estado;
import com.victor.vendas.domain.Produto;
import com.victor.vendas.domain.enums.TipoCliente;
import com.victor.vendas.repositories.CategoriaRepository;
import com.victor.vendas.repositories.CidadeRepository;
import com.victor.vendas.repositories.ClienteRepository;
import com.victor.vendas.repositories.EnderecoRepository;
import com.victor.vendas.repositories.EstadoRepository;
import com.victor.vendas.repositories.ProdutoRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Setup");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Siva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);	
		
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardin", "38220834", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, cli1);
		
		cat1.setProdutos(Arrays.asList(p1, p2, p3));
		cat2.setProdutos(Arrays.asList(p2));
		
		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat1, cat2));
		p3.setCategorias(Arrays.asList(cat1));
		
		est1.setCidades(Arrays.asList(c1));
		est2.setCidades(Arrays.asList(c2, c3));
		
		cli1.setEnderecos(Arrays.asList(e1, e2));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
