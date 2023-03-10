package br.com.library.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.library.test.utils.ClassBuilder;

class ResponseAddressDTOTest {
	
	private ResponseAddressDTO expectedResponseAddressDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedResponseAddressDTO = ClassBuilder.addressDTOBuilder();
	}

	@Test
	void builder() {
		ResponseAddressDTO responseAddressDTO = ResponseAddressDTO.builder()
				.cep("04447-010")
				.number(56)
				.street("Avenida Nossa Senhora do Sabará")
				.neighborhood("Vila Emir")
				.city("São Paulo")
				.state("SP")
				.build();
		assertEquals(expectedResponseAddressDTO.toString(), responseAddressDTO.toString());
	}
	
	@Test
	void setter() {
		ResponseAddressDTO responseAddressDTO = new ResponseAddressDTO();
		responseAddressDTO.setCity("São Paulo");
		responseAddressDTO.setNeighborhood("Vila Emir");
		responseAddressDTO.setNumber(56);
		responseAddressDTO.setStreet("Avenida Nossa Senhora do Sabará");
		responseAddressDTO.setCep("04447-010");
		responseAddressDTO.setState("SP");
		assertEquals(expectedResponseAddressDTO.toString(), responseAddressDTO.toString());
	}

}
