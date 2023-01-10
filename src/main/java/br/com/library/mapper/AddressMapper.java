package br.com.library.mapper;

import br.com.library.model.Address;
import br.com.library.model.Customer;
import br.com.library.model.dto.RequestDTO;
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
	
	public static Address viaCepToModel(ViaCepDTO viaCepDTO, RequestDTO requestDTO) {
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
