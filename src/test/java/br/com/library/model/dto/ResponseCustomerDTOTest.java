package br.com.library.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.library.test.utils.ClassBuilder;

class ResponseCustomerDTOTest {
	
	private ResponseCustomerDTO expectedResponseCustomerDTO;
	private ResponseAddressDTO responseAddressDTO;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseCustomerDTO = ClassBuilder.responseCustomerDTOBuilder();
		responseAddressDTO = ClassBuilder.addressDTOBuilder();
		expectedResponseCustomerDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseCustomerDTO responseCustomerDTO = ResponseCustomerDTO.builder()
				.id(id)
				.name("Caio Castro")
				.cpf("12365478965")
				.phone("1111111111")
				.email("caio@castro.com")
				.responseAddressDTO(responseAddressDTO)
				.build();
		assertEquals(expectedResponseCustomerDTO.toString(), responseCustomerDTO.toString());
	}

	@Test
	void setter() {
		ResponseCustomerDTO responseCustomerDTO = new ResponseCustomerDTO();
		responseCustomerDTO.setName("Caio Castro");
		responseCustomerDTO.setId(id);
		responseCustomerDTO.setCpf("12365478965");
		responseCustomerDTO.setPhone("1111111111");
		responseCustomerDTO.setEmail("caio@castro.com");
		responseCustomerDTO.setResponseAddressDTO(responseAddressDTO);
		assertEquals(expectedResponseCustomerDTO.toString(), responseCustomerDTO.toString());
	}
	
}
