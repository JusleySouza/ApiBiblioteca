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
import br.com.library.model.Customer;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.RequestCustomerDTO;
import br.com.library.model.dto.ResponseCustomerDTO;
import br.com.library.repository.CustomerRepository;
import br.com.library.services.AddressService;
import br.com.library.services.PaginationService;
import br.com.library.test.utils.ClassBuilder;

class CustomerServiceImplementTest {
	
	@InjectMocks
	private CustomerServiceImplement services;
	
	@Mock
	private Validator mockValidator;
	
	@Mock
	private PaginationService paginationService;
	
	@Mock
	private CustomerRepository repository;
	
	@Mock
	private AddressService addressService;
	
	private Customer customer;
	private RequestCustomerDTO requestCustomerDTO;
	private ResponseCustomerDTO responseCustomerDTO;
	private LocalValidatorFactoryBean validator;
	private Set<ConstraintViolation<Object>> violations;
	private Pageable page;
	private ListCustomer list;
	private PaginationServiceImplement pag;
	private Address address;
	
	private Page<Customer> pages;
	private Page<Customer> pagesEmpty;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		UUID id = UUID.randomUUID();
		customer = ClassBuilder.customerBuilder();
		requestCustomerDTO = ClassBuilder.requestCustomerDTOBuilder();
		responseCustomerDTO = ClassBuilder.responseCustomerDTOBuilder();
		list = new ListCustomer();
		
		pag = new PaginationServiceImplement();
		page = pag.createPagination(1, 1, "name, DESC");
		pagesEmpty = new PageImpl<Customer>(List.of(), page, 1l);
		pages = new PageImpl<Customer>(List.of(customer), page, 1l);
		
		responseCustomerDTO.setId(id);
		customer.setId(id);
		
		validator = new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
	}

	@Test
	public void findByCpf() throws Exception {
		when(repository.findByCpf(anyString())).thenReturn(customer);
		ResponseCustomerDTO cust = services.findByCpf("12345678965");
		assertEquals(cust.toString(), responseCustomerDTO.toString());
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
		when(paginationService.paginationCustomer(any(), any())).thenReturn(list);
		ListCustomer listCustomers = services.findByCep("04447-010", 6, 1, "name, DESC");
		assertNotNull(listCustomers);
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
		when(paginationService.paginationCustomer(any(), any())).thenReturn(list);
		when(paginationService.createPagination(anyInt(), anyInt(), anyString())).thenReturn(page);
		ListCustomer customers = services.findAll( 6, 1, "name, DESC");
		assertNotNull(customers);
	}

	@Test
	public void delete() {
		customer.setActive(false);
		when(repository.findById(any())).thenReturn(Optional.of(customer));
		Customer customers = (Customer) services.delete(UUID.randomUUID());
		assertTrue(customers.getActive().equals(false));
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
		when(addressService.getAddressByViaCep(requestCustomerDTO)).thenReturn(address);
		ResponseEntity<Object>  customer = services.create(requestCustomerDTO);
		assertTrue(customer.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test
	public void createWithMissingFields() {
		requestCustomerDTO.setCpf(null);
		violations = validator.validate(requestCustomerDTO);
		when(mockValidator.validate(any())).thenReturn(violations);
		ResponseEntity<Object> cust = services.create(requestCustomerDTO);
		assertTrue(cust.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
	@Test
	public void createWithDuplicateCpf() {
		String messageError = "Cpf already registered";
		when(repository.findByCpf(anyString())).thenReturn(customer);
		String message = assertThrows(DuplicateDocumentsException.class, () -> {
			services.create(requestCustomerDTO);
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void update() {
		when(repository.findById(any())).thenReturn(Optional.of(customer));
		when(addressService.getAddressByViaCep(requestCustomerDTO)).thenReturn(address);
		ResponseEntity<Object> customer = services.update(requestCustomerDTO, UUID.randomUUID());
		assertTrue(customer.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void updateResourceNotFoundException() {
		String messageError = "No records found for this id!!";
		when(repository.findById(any())).thenReturn(Optional.ofNullable(null));
		String message = assertThrows(ResourceNotFoundException.class, () -> {
			services.update(requestCustomerDTO, UUID.randomUUID());
		}).getMessage();
		assertEquals(messageError, message);
	}
	
	@Test
	public void updateWithMissingFields() {
		requestCustomerDTO.setCpf(null);
		violations = validator.validate(requestCustomerDTO);
		when(mockValidator.validate(any())).thenReturn(violations);
		ResponseEntity<Object> customer = services.update(requestCustomerDTO, UUID.randomUUID());
		assertTrue(customer.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY));
	}
	
}
