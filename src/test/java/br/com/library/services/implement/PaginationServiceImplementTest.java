package br.com.library.services.implement;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.library.model.Customer;
import br.com.library.model.Employee;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.ListEmployee;
import br.com.library.model.dto.ResponseCustomerDTO;
import br.com.library.model.dto.ResponseEmployeeDTO;
import br.com.library.test.utils.ClassBuilder;

class PaginationServiceImplementTest {
	
	@InjectMocks
	private PaginationServiceImplement service;
	
	private Customer customer;
	private Employee employee;
	private Page<Customer> listPageCustomer;
	private Page<Employee> listPageEmployee;
	private List<ResponseCustomerDTO> customers;
	private List<ResponseEmployeeDTO> employees;
	private ResponseCustomerDTO responseCustomerDTO;
	private ResponseEmployeeDTO responseEmployeeDTO;
	private ListCustomer listCustomer;
	private ListEmployee listEmployee;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		customer = ClassBuilder.customerBuilder();
		responseCustomerDTO = ClassBuilder.responseCustomerDTOBuilder();
		Sort sortOrderIgnoreCase = Sort.by(new Sort.Order(Sort.Direction.DESC, "name"));
		PageRequest page = PageRequest.of(1, 1, sortOrderIgnoreCase);
		listPageCustomer = new PageImpl<Customer>(List.of(customer), page, 1l);
		customers = new ArrayList<>();
		customers.add(responseCustomerDTO);
		
		employee = ClassBuilder.employeeBuilder();
		responseEmployeeDTO = ClassBuilder.responseEmployeeDTOBuilder();
		listPageEmployee = new PageImpl<Employee>(List.of(employee), page, 1l);
		employees = new ArrayList<>();
		employees.add(responseEmployeeDTO);
	}

	@Test
	public void paginationCustomer() {
		listCustomer = service.paginationCustomer(listPageCustomer, customers);
		assertNotNull(listCustomer);
	}
	
	@Test
	public void paginationEmployee() {
		listEmployee = service.paginationEmployee(listPageEmployee, employees);
		assertNotNull(listEmployee);
	}

}
