package br.com.library.mapper;

import java.time.LocalDate;

import br.com.library.model.Customer;
import br.com.library.model.dto.RequestCustomerDTO;
import br.com.library.model.dto.ResponseCustomerDTO;

public final class CustomerMapper {
	
	public static Customer requestDTOToModel(RequestCustomerDTO requestDTO) {
		return Customer.builder()
				.name(requestDTO.getName())
				.cpf(requestDTO.getCpf())
				.phone(requestDTO.getPhone())
				.email(requestDTO.getEmail())
				.created(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
	}
	
	public static ResponseCustomerDTO modelToResponseDTO(Customer customer) {
		return ResponseCustomerDTO.builder()
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
	
	public static Customer updateCustomer(Customer customer, RequestCustomerDTO RequestDTO) {
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
