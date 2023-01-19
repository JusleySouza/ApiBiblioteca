package br.com.library.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.library.test.utils.ClassBuilder;

class RequestEmployeeDTOTest {
	
	private RequestEmployeeDTO expectedRequestEmployeeDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedRequestEmployeeDTO = ClassBuilder.requestEmployeeDTOBuilder();
	}
	
	@Test
	void builder() {
		RequestEmployeeDTO requestEmployeeDTO = RequestEmployeeDTO.builder()
				.cpf("12365478965")
				.email("caio@castro.com")
				.name("Caio Castro")
				.phone("1111111111")
				.position("Gerente")
				.cep("04447-010")
				.number(56)
				.build();
		assertEquals(expectedRequestEmployeeDTO.toString(), requestEmployeeDTO.toString());
	}

	@Test
	void setter() {
		RequestEmployeeDTO requestEmployeeDTO = new RequestEmployeeDTO();
		requestEmployeeDTO.setCpf("12365478965");
		requestEmployeeDTO.setEmail("caio@castro.com");
		requestEmployeeDTO.setName("Caio Castro");
		requestEmployeeDTO.setPhone("1111111111");
		requestEmployeeDTO.setCep("04447-010");
		requestEmployeeDTO.setPosition("Gerente");
		requestEmployeeDTO.setNumber(56);
		assertEquals(expectedRequestEmployeeDTO.toString(), requestEmployeeDTO.toString());
	}

}
