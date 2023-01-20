package com.victor.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.victor.vendas.domain.Categoria;
import com.victor.vendas.domain.Cliente;
import com.victor.vendas.dto.ClienteDTO;
import com.victor.vendas.repositories.ClienteRepository;
import com.victor.vendas.services.exceptions.DataIntegrityException;
import com.victor.vendas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repo;
	
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
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente update(Cliente objUpdate) {
		Cliente obj = findById(objUpdate.getId());
		obj = updateData(objUpdate, obj);
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente que possui produto");
		}
	}
	
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), objDto.getTipo());
	}
	
	private Cliente updateData(Cliente objUpdate, Cliente obj) {
		obj.setNome(objUpdate.getNome() == null? obj.getNome(): objUpdate.getNome());
		obj.setEmail(objUpdate.getEmail() == null? obj.getEmail(): objUpdate.getEmail());
		obj.setCpfOuCnpj(objUpdate.getCpfOuCnpj() == null? obj.getCpfOuCnpj(): objUpdate.getCpfOuCnpj());
		obj.setTipo(objUpdate.getTipo() == null? obj.getTipo(): objUpdate.getTipo());
		return obj;
	}
	
	
}







