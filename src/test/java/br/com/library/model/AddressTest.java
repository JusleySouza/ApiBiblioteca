package br.com.library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.library.test.utils.ClassBuilder;

class AddressTest {
	
	private Address expectedAddress;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedAddress = ClassBuilder.addressBuilder();
		expectedAddress.setId(id);
	}

	@Test
	void build() {
		Address address = Address.builder()
				.city("São Paulo")
				.neighborhood("Jardim Campo Grande")
				.number(56)
				.street("Av. Nossa Senhora do Sabará")
				.cep("04447-010")
				.state("SP")
				.id(id)
				.build();		
		assertEquals(expectedAddress.toString(), address.toString());
	}
	
	@Test
	void setter() {
		Address address = new Address();
		address.setCity("São Paulo");
		address.setNeighborhood("Jardim Campo Grande");
		address.setNumber(56);
		address.setStreet("Av. Nossa Senhora do Sabará");
		address.setCep("04447-010");
		address.setState("SP");
		address.setId(id);
		assertEquals(expectedAddress.toString(), address.toString());
	}

}
