package com.victor.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.vendas.domain.Cidade;
import com.victor.vendas.domain.Cliente;
import com.victor.vendas.domain.Endereco;
import com.victor.vendas.domain.enums.TipoCliente;
import com.victor.vendas.dto.ClienteDTO;
import com.victor.vendas.dto.ClienteNewDTO;
import com.victor.vendas.repositories.ClienteRepository;
import com.victor.vendas.repositories.EnderecoRepository;
import com.victor.vendas.services.exceptions.DataIntegrityException;
import com.victor.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	ClienteRepository repo;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Page<Cliente> findPage(Integer page, Integer lines, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, lines, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public List<Cliente> findAll(){
		List<Cliente> list = repo.findAll();
		return list; 
	}
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Cliente com id: " + id + " não encontrado no banco de dados." + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque essa entidade possui pedidos relacionados");
		}
	}
	
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	public Cliente fromDto(ClienteNewDTO objDto) {
		Cliente cli = new  Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cid, cli);
		enderecoRepository.save(end);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setCpfOuCnpj(obj.getCpfOuCnpj());
		newObj.setTipo(obj.getTipo());
	}
	
	
}







