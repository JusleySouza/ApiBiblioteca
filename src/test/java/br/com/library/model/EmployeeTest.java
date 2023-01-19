package br.com.library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.library.test.utils.ClassBuilder;

class EmployeeTest {

	private Employee expectedEmployee;
	
	private UUID id;
	
	private Address address;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		address = ClassBuilder.addressBuilder();
		expectedEmployee = ClassBuilder.employeeBuilder();
		expectedEmployee.setId(id);
		expectedEmployee.setAddress(address);
	}

	@Test
	void builder() {
		Employee employee = Employee.builder()
				.active(true)
				.changed(LocalDate.now())
				.cpf("12365478965")
				.created(LocalDate.now())
				.email("caio@castro.com")
				.name("Caio Castro")
				.phone("1111111111")
				.position("Gerente")
				.id(id)
				.address(address)
				.build();
		assertEquals(expectedEmployee.toString(), employee.toString());
	}

	@Test
	void setter() {
		Employee employee = new Employee();
		employee.setActive(true);
		employee.setChanged(LocalDate.now());
		employee.setCpf("12365478965");
		employee.setCreated(LocalDate.now());
		employee.setEmail("caio@castro.com");
		employee.setName("Caio Castro");
		employee.setPhone("1111111111");
		employee.setPosition("Gerente");
		employee.setId(id);
		employee.setAddress(address);
		assertEquals(expectedEmployee.toString(), employee.toString());
	}
	
}
