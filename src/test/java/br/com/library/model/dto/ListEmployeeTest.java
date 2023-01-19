package br.com.library.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.library.test.utils.ClassBuilder;

class ListEmployeeTest {
	
	private ListEmployee expectedListEmployee;
	private Pagination pagination;
	private ResponseEmployeeDTO responseEmployeeDTO;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedListEmployee = ClassBuilder.listEmployeeBuilder();
		pagination = ClassBuilder.paginationBuilder();
		responseEmployeeDTO = ClassBuilder.responseEmployeeDTOBuilder();
		responseEmployeeDTO.setId(id);
		expectedListEmployee.getContent().get(0).setId(id);
	}

	@Test
	void setter() {
		ListEmployee listEmployee = new ListEmployee();
		listEmployee.setPageable(pagination);
		listEmployee.setContent(List.of(responseEmployeeDTO));
		assertEquals(expectedListEmployee.toString(), listEmployee.toString());
	}

}
