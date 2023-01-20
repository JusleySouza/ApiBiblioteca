package br.com.library.services.implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.library.exception.DuplicateDocumentsException;
import br.com.library.exception.ResourceNotFoundException;
import br.com.library.model.Address;
import br.com.library.model.Employee;
import br.com.library.model.dto.ListEmployee;
import br.com.library.model.dto.RequestEmployeeDTO;
import br.com.library.model.dto.ResponseEmployeeDTO;
import br.com.library.repository.EmployeeRepository;
import br.com.library.services.AddressService;
import br.com.library.services.PaginationService;
import br.com.library.test.utils.ClassBuilder;

class EmployeeServiceImplementTest {
	
	@InjectMocks
	private EmployeeServiceImplement services;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private PaginationService paginationService;
	
	@Mock
	private EmployeeRepository repository;
	
	@Mock
	private AddressService addressService;
	
	private Employee employee;
	private RequestEmployeeDTO requestEmployeeDTO;
	private ResponseEmployeeDTO responseEmployeeDTO;
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;
	private Pageable page;
	private ListEmployee list;
	private PaginationServiceImplement pag;
	private Address address;
	
	private Page<Employee> pages;
	private Page<Employee> pagesEmpty;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		UUID id = UUID.randomUUID();
		employee = ClassBuilder.employeeBuilder();
		requestEmployeeDTO = ClassBuilder.requestEmployeeDTOBuilder();
		responseEmployeeDTO = ClassBuilder.responseEmployeeDTOBuilder();
		list = new ListEmployee();
		
		pag = new PaginationServiceImplement();
		page = pag.createPagination(1, 1, "name, DESC");
		pagesEmpty = new PageImpl<Employee>(List.of(), page, 1l);
		pages = new PageImpl<Employee>(List.of(employee), page, 1l);
		
		responseEmployeeDTO.setId(id);
		employee.setId(id);
		
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
	}

	@Test
	public void findByCpf() throws Exception {
		when(repository.findByCpf(anyString())).thenReturn(employee);
		ResponseEmployeeDTO emp = services.findByCpf("12365478965");
		assertEquals(emp.toString(), responseEmployeeDTO.toString());
	}

	@Test
	public void findByCpfResourceNotFoundException(){
		String messageError = "No records found for this cpf!!";
		when(repository.findByCpf(anyString())).thenReturn(null);
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByCpf("12345678965");
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void findByCep() {
		when(repository.findAllByAddressCepAndActiveTrue(anyString(), any())).thenReturn(pages);
		when(paginationService.createPagination(anyInt(), anyInt(), anyString())).thenReturn(page);
		when(paginationService.paginationEmployee(any(), any())).thenReturn(list);
		ListEmployee listEmployees = services.findByCep("04447-010", 6, 1, "name, DESC");
		assertNotNull(listEmployees);
	}
	
	@Test
	public void findByCepResourceNotFoundException() {
		String messageError = "No records found for this cep!!";
		when(repository.findAllByAddressCepAndActiveTrue(anyString(), any())).thenReturn(pagesEmpty);
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.findByCep("04447-010", 6, 1, "name, DESC");
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void findAll() {
		when(repository.findAllByActiveTrue(any())).thenReturn(pages);
		when(paginationService.paginationEmployee(any(), any())).thenReturn(list);
		when(paginationService.createPagination(anyInt(), anyInt(), anyString())).thenReturn(page);
		ListEmployee employees = services.findAll( 6, 1, "name, DESC");
		assertNotNull(employees);
	}
	
	@Test
	public void delete() {
		employee.setActive(false);
		when(repository.findById(any())).thenReturn(Optional.of(employee));
		Employee employees = (Employee) services.delete(UUID.randomUUID());
		assertTrue(employees.getActive().equals(false));
	}
	
	@Test
	public void deleteResourceNotFoundException() {
		String messageError = "No records found for this id!!";
		when(repository.findById(any())).thenReturn(Optional.ofNullable(null));
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.delete(UUID.randomUUID());
		}).getMessage();		
		assertEquals(messageError, message);
	}
	
	@Test
	public void create() {
		when(addressService.getAddressByViaCep(requestEmployeeDTO)).thenReturn(address);
		ResponseEntity<Object>  employee = services.create(requestEmployeeDTO);
		assertTrue(employee.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test
	public void createWithMissingFields() {
		requestEmployeeDTO.setCpf(null);
		violations = validator.validate(requestEmployeeDTO);
		when(mockValidator.validate(any())).thenReturn(violations);
		ResponseEntity<Object> emp = services.create(requestEmployeeDTO);
		assertTrue(emp.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	@Test
	public void createWithDuplicateCpf() {
		String messageError = "Cpf already registered";
		when(repository.findByCpf(anyString())).thenReturn(employee);
		String message = assertThrows(DuplicateDocumentsException.class, () -> {
			services.create(requestEmployeeDTO);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(employee));
		when(addressService.getAddressByViaCep(requestEmployeeDTO)).thenReturn(address);
		ResponseEntity<Object> employee = services.update(requestEmployeeDTO, UUID.randomUUID());
		assertTrue(employee.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "No records found for this id!!";
		when(repository.findById(any())).thenReturn(Optional.ofNullable(null));
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(requestEmployeeDTO, UUID.randomUUID());
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void updateWithMissingFields() {
		requestEmployeeDTO.setCpf(null);
		violations = validator.validate(requestEmployeeDTO);
		when(mockValidator.validate(any())).thenReturn(violations);
		ResponseEntity<Object> employee = services.update(requestEmployeeDTO, UUID.randomUUID());
		assertTrue(employee.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
}
