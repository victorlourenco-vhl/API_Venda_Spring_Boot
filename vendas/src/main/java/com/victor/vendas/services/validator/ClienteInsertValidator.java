package com.victor.vendas.services.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.victor.vendas.domain.Cliente;
import com.victor.vendas.domain.enums.TipoCliente;
import com.victor.vendas.dto.ClienteNewDTO;
import com.victor.vendas.repositories.ClienteRepository;
import com.victor.vendas.resources.exceptions.FieldMessage;
import com.victor.vendas.services.validator.utils.BR;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
			list.add(new FieldMessage("cpfOuCnpj", "O CPF informado é inválido"));
		}
			
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
			list.add(new FieldMessage("cpfOuCnpj", "O CNPJ informado é inválido"));
		}
			
		
		if(objDto.getTipo() == null) {
				list.add(new FieldMessage("tipo", "O tipo não pode ser nulo"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if(aux != null) {
			list.add(new FieldMessage("email", "Esse email já está cadastrado"));
		}

		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessage e : list) {
			 context.disableDefaultConstraintViolation();
			 context.buildConstraintViolationWithTemplate(e.getMessage())
			 .addPropertyNode(e.getFieldMessage()).addConstraintViolation();
			 }
		
		
		return list.isEmpty();
	}
}