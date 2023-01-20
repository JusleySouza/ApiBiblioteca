package br.com.library.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.library.test.utils.ClassBuilder;

class ResponseEmployeeDTOTest {
	
	private ResponseEmployeeDTO expectedResponseEmployeeDTO;
	private ResponseAddressDTO responseAddressDTO;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseEmployeeDTO = ClassBuilder.responseEmployeeDTOBuilder();
		responseAddressDTO = ClassBuilder.addressDTOBuilder();
		expectedResponseEmployeeDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseEmployeeDTO responseEmployeeDTO = ResponseEmployeeDTO.builder()
				.id(id)
				.name("Caio Castro")
				.cpf("12365478965")
				.phone("1231231231")
				.email("caio@castro.com")
				.position("Gerente")
				.responseAddressDTO(responseAddressDTO)
				.build();
		assertEquals(expectedResponseEmployeeDTO.toString(), responseEmployeeDTO.toString());
	}
	
	@Test
	void setter() {
		ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO();
		responseEmployeeDTO.setName("Caio Castro");
		responseEmployeeDTO.setId(id);
		responseEmployeeDTO.setCpf("12365478965");
		responseEmployeeDTO.setPhone("1231231231");
		responseEmployeeDTO.setEmail("caio@castro.com");
		responseEmployeeDTO.setPosition("Gerente");
		responseEmployeeDTO.setResponseAddressDTO(responseAddressDTO);
		assertEquals(expectedResponseEmployeeDTO.toString(), responseEmployeeDTO.toString());
	}

}
