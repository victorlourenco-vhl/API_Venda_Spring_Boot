package com.victor.vendas.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.vendas.domain.Categoria;
import com.victor.vendas.domain.Cidade;
import com.victor.vendas.domain.Cliente;
import com.victor.vendas.domain.Endereco;
import com.victor.vendas.domain.Estado;
import com.victor.vendas.domain.ItemPedido;
import com.victor.vendas.domain.Pagamento;
import com.victor.vendas.domain.PagamentoComBoleto;
import com.victor.vendas.domain.PagamentoComCartao;
import com.victor.vendas.domain.Pedido;
import com.victor.vendas.domain.Produto;
import com.victor.vendas.domain.enums.EstadoPagamento;
import com.victor.vendas.domain.enums.TipoCliente;
import com.victor.vendas.repositories.CategoriaRepository;
import com.victor.vendas.repositories.CidadeRepository;
import com.victor.vendas.repositories.ClienteRepository;
import com.victor.vendas.repositories.EnderecoRepository;
import com.victor.vendas.repositories.EstadoRepository;
import com.victor.vendas.repositories.ItemPedidoRepository;
import com.victor.vendas.repositories.PagamentoRepository;
import com.victor.vendas.repositories.PedidoRepository;
import com.victor.vendas.repositories.ProdutoRepository;

@Service
public class DBService {

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

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Corte e costura");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Jardinagem");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de Escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		Cliente cli1 = new Cliente(null, "Maria Siva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardin", "38220834", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "10", "Sala 800", "Centro", "38777012", c2, cli1);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 1, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.0);

		cat1.setProdutos(Arrays.asList(p1, p2, p3));
		cat2.setProdutos(Arrays.asList(p2, p4));
		cat3.setProdutos(Arrays.asList(p5, p6));
		cat4.setProdutos(Arrays.asList(p1, p2, p3, p7));
		cat5.setProdutos(Arrays.asList(p8));
		cat6.setProdutos(Arrays.asList(p9, p10));
		cat7.setProdutos(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);

		est1.setCidades(Arrays.asList(c1));
		est2.setCidades(Arrays.asList(c2, c3));

		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().add(ip3);

		cli1.setEnderecos(Arrays.asList(e1, e2));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		cli1.setPedidos(Arrays.asList(ped1, ped2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
