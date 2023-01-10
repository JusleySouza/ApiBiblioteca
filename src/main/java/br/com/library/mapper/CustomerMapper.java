package br.com.library.mapper;

import java.time.LocalDate;

import br.com.library.model.Customer;
import br.com.library.model.dto.RequestDTO;
import br.com.library.model.dto.ResponseDTO;

public final class CustomerMapper {
	
	public static Customer requestDTOToModel(RequestDTO requestDTO) {
		return Customer.builder()
				.name(requestDTO.getName())
				.cpf(requestDTO.getCpf())
				.phone(requestDTO.getPhone())
				.email(requestDTO.getEmail())
				.created(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
	}
	
	public static ResponseDTO modelToResponseDTO(Customer customer) {
		return ResponseDTO.builder()
				.id(customer.getId())
				.name(customer.getName())
				.cpf(customer.getCpf())
				.phone(customer.getPhone())
				.email(customer.getEmail())
				.responseAddressDTO(AddressMapper.modelToResponseAddressDTO(customer))
				.build();
	}
	
	public static Customer customerDelete(Customer customer) {
		customer.setActive(Boolean.FALSE);
		customer.setChanged(LocalDate.now());
		return customer;
	}
	
	public static Customer updateCustomer(Customer customer, RequestDTO RequestDTO) {
		return Customer.builder()
				.name(RequestDTO.getName())
				.cpf(RequestDTO.getCpf())
				.phone(RequestDTO.getPhone())
				.email(RequestDTO.getEmail())
				.changed(LocalDate.now())
				.id(customer.getId())
				.created(customer.getCreated())
				.active(customer.getActive())
				.build();
	}
	
}
