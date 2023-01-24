package br.com.library.services.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.com.library.exception.ResourceNotFoundException;
import br.com.library.model.Address;
import br.com.library.model.dto.RequestCustomerDTO;
import br.com.library.model.dto.RequestEmployeeDTO;
import br.com.library.test.utils.ClassBuilder;

class AddressServiceImplementTest {
	
	@InjectMocks
	private AddressServiceImplement service;
	
	private Address address;
	private Address expectedAddress;
	private RequestCustomerDTO requestCustomerDTO;
	private RequestEmployeeDTO requestEmployeeDTO;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		expectedAddress = ClassBuilder.addressBuilder();
		requestCustomerDTO = ClassBuilder.requestCustomerDTOBuilder();
		requestEmployeeDTO = ClassBuilder.requestEmployeeDTOBuilder();
		expectedAddress.setId(null);
		
	}

	@Test
	public void getAddressByViaCepCustomer() {
		address = service.getAddressByViaCep(requestCustomerDTO);
		assertEquals(expectedAddress.toString(), address.toString());
	}
	
	@Test
	public void getAddressByViaCepEmployee() {
		address = service.getAddressByViaCep(requestEmployeeDTO);
		assertEquals(expectedAddress.toString(), address.toString());
	}
	
	@Test
	public void getAddressByViaCepCustomerError() {
		String messageError = "Cep not found";
		requestCustomerDTO.setCep("11111-111");
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			service.getAddressByViaCep(requestCustomerDTO);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void getAddressByViaCepEmployeeError() {
		String messageError = "Cep not found";
		requestEmployeeDTO.setCep("11111-111");
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			service.getAddressByViaCep(requestEmployeeDTO);
		}).getMessage();
		assertEquals(messageError, message);
	}

}
