package br.com.library.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.library.test.utils.ClassBuilder;

class ViaCepDTOTest {
	
	private ViaCepDTO expectedViaCepDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedViaCepDTO = ClassBuilder.viaCepDTOBuilder();
	}

	@Test
	void setter() {	
		ViaCepDTO viaCepDTO = new ViaCepDTO();
		viaCepDTO.setCep("04447-010");;
		viaCepDTO.setLogradouro("Avenida Nossa Senhora do Sabará");;
		viaCepDTO.setBairro("Vila Emir");;
		viaCepDTO.setLocalidade("São Paulo");;
		viaCepDTO.setUf("SP");;
		viaCepDTO.setErro(false);
		assertEquals(expectedViaCepDTO.toString(), viaCepDTO.toString());
	}

}
