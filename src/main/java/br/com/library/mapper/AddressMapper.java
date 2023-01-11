package br.com.library.mapper;

import br.com.library.model.Address;
import br.com.library.model.Customer;
import br.com.library.model.Employee;
import br.com.library.model.dto.RequestCustomerDTO;
import br.com.library.model.dto.RequestEmployeeDTO;
import br.com.library.model.dto.ResponseAddressDTO;
import br.com.library.model.dto.ViaCepDTO;

public final class AddressMapper {

	public static ResponseAddressDTO modelToResponseAddressDTO(Customer customer) {
		return ResponseAddressDTO.builder()
				.cep(customer.getAddress().getCep())
				.number(customer.getAddress().getNumber())
				.street(customer.getAddress().getStreet())
				.neighborhood(customer.getAddress().getNeighborhood())
				.city(customer.getAddress().getCity())
				.state(customer.getAddress().getState())
				.build();
	}
	
	public static ResponseAddressDTO modelToResponseAddressDTO(Employee employee) {
		return ResponseAddressDTO.builder()
				.cep(employee.getAddress().getCep())
				.number(employee.getAddress().getNumber())
				.street(employee.getAddress().getStreet())
				.neighborhood(employee.getAddress().getNeighborhood())
				.city(employee.getAddress().getCity())
				.state(employee.getAddress().getState())
				.build();
	}
	
	public static Address viaCepToModel(ViaCepDTO viaCepDTO, RequestCustomerDTO requestDTO) {
		return Address.builder()
				.street(viaCepDTO.getLogradouro())
				.cep(requestDTO.getCep())
				.number(requestDTO.getNumber())
				.neighborhood(viaCepDTO.getBairro())
				.city(viaCepDTO.getLocalidade())
				.state(viaCepDTO.getUf())
				.build();
	}
	
	public static Address viaCepToModel(ViaCepDTO viaCepDTO, RequestEmployeeDTO requestDTO) {
		return Address.builder()
				.street(viaCepDTO.getLogradouro())
				.cep(requestDTO.getCep())
				.number(requestDTO.getNumber())
				.neighborhood(viaCepDTO.getBairro())
				.city(viaCepDTO.getLocalidade())
				.state(viaCepDTO.getUf())
				.build();
	}

}
