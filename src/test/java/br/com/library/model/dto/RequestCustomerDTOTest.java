package br.com.library.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.library.test.utils.ClassBuilder;

class RequestCustomerDTOTest {
	
	private RequestCustomerDTO expectedRequestCustomerDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedRequestCustomerDTO = ClassBuilder.requestCustomerDTOBuilder();
	}

	@Test
	void builder() {
		RequestCustomerDTO requestCustomerDTO = RequestCustomerDTO.builder()
				.cpf("12365478965")
				.email("caio@castro.com")
				.name("Caio Castro")
				.phone("1111111111")
				.cep("04447-010")
				.number(56)
				.build();
		assertEquals(expectedRequestCustomerDTO.toString(), requestCustomerDTO.toString());
	}
	
	@Test
	void setter() {
		RequestCustomerDTO requestCostumerDTO = new RequestCustomerDTO();
		requestCostumerDTO.setCpf("12365478965");
		requestCostumerDTO.setEmail("caio@castro.com");
		requestCostumerDTO.setName("Caio Castro");
		requestCostumerDTO.setPhone("1111111111");
		requestCostumerDTO.setCep("04447-010");
		requestCostumerDTO.setNumber(56);
		assertEquals(expectedRequestCustomerDTO.toString(), requestCostumerDTO.toString());
	}

}
