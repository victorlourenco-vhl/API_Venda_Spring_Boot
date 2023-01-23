package com.victor.vendas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

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
			throw new DataIntegrityException("Não é possível excluir uma cliente que possui produto");
		}
	}
	
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), objDto.getTipo());
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome() == null? newObj.getNome(): obj.getNome());
		newObj.setEmail(obj.getEmail() == null? newObj.getEmail():obj.getEmail());
		newObj.setCpfOuCnpj(obj.getCpfOuCnpj() == null? newObj.getCpfOuCnpj(): obj.getCpfOuCnpj());
		newObj.setTipo(obj.getTipo() == null? newObj.getTipo(): obj.getTipo());
	}
	
	
}







