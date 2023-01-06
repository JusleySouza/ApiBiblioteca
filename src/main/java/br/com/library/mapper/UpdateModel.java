package br.com.library.mapper;

import java.time.LocalDate;

import br.com.library.model.Customer;
import br.com.library.model.dto.RequestDTO;
import lombok.Generated;

@Generated
public class UpdateModel {
	
	public static Customer customer(Customer customer, RequestDTO RequestDTO) {
		customer.setName(RequestDTO.getName());
		customer.setCpf(RequestDTO.getCpf());
		customer.setPhone(RequestDTO.getPhone());
		customer.setEmail(RequestDTO.getEmail());
		customer.setChanged(LocalDate.now());
		return customer;
	}

}
