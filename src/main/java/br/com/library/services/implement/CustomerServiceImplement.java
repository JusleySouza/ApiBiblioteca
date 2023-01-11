package br.com.library.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.library.config.LoggerConfig;
import br.com.library.exception.DuplicateDocumentsException;
import br.com.library.exception.ResourceNotFoundException;
import br.com.library.mapper.CustomerMapper;
import br.com.library.model.Customer;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.RequestCustomerDTO;
import br.com.library.model.dto.ResponseCustomerDTO;
import br.com.library.model.dto.error.ResponseError;
import br.com.library.repository.CustomerRepository;
import br.com.library.services.AddressService;
import br.com.library.services.CustomerService;
import br.com.library.services.PaginationService;

@Component
public class CustomerServiceImplement implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private AddressService address;

	@Autowired
	private Validator validator;
	
	@Autowired
	private PaginationService paginationService;

	private Customer customer;
	private ResponseCustomerDTO responseDTO;
	private Page<Customer> pageListResponse;
	private List<ResponseCustomerDTO> listResponse;
	private ListCustomer response;

	@Override
	@Cacheable(cacheNames = "Customers", key = "#root.method.name")
	public ListCustomer findAll(Integer pageSize, Integer page, String sortBy) {
		
		Pageable pageable = paginationService.createPagination(pageSize, page, sortBy);
		
		listResponse = new ArrayList<>();
		pageListResponse = repository.findAllByActiveTrue(pageable);

		for (Customer customer : pageListResponse) {
			responseDTO = CustomerMapper.modelToResponseDTO(customer);
			listResponse.add(responseDTO);
		}
		
		response = paginationService.paginationCustomer(pageListResponse, listResponse);
		
		LoggerConfig.LOGGER_CUSTOMER.info(" Customer List successfully executed!! ");
		
		return response;
	}

	@Override
	@Cacheable(cacheNames = "Customers", key = "#cpf")
	public ResponseCustomerDTO findByCpf(String cpf) {
		customer = repository.findByCpf(cpf);
		
		if(customer == null) {
			LoggerConfig.LOGGER_CUSTOMER.error("No records found for this cpf!!");
			throw new ResourceNotFoundException("No records found for this cpf!!");
		}
		
		responseDTO = CustomerMapper.modelToResponseDTO(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Customer found successfully!! ");
		return responseDTO;
	}

	@Override
	public ResponseEntity<Object> create(RequestCustomerDTO requestCustomerDTO) {
		Set<ConstraintViolation<RequestCustomerDTO>> violations = validator.validate(requestCustomerDTO);

		if (!violations.isEmpty()) {
			LoggerConfig.LOGGER_CUSTOMER.error("Validation error");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		customer = CustomerMapper.requestDTOToModel(requestCustomerDTO);
		
		String message = duplicateDocumentValidator(customer);
		if(!message.isEmpty()) {
			LoggerConfig.LOGGER_CUSTOMER.error("Duplicate documents");
			throw new DuplicateDocumentsException(message);
		}
		
		customer.setAddress(address.getAddressByViaCep(requestCustomerDTO));
		repository.save(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Customer " + customer.getName() + " saved successfully!!");
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Object> update(RequestCustomerDTO requestCustomerDTO, UUID customerId) {
		Set<ConstraintViolation<RequestCustomerDTO>> violations = validator.validate(requestCustomerDTO);

		if (!violations.isEmpty()) {
			LoggerConfig.LOGGER_CUSTOMER.error("Validation error");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		customer = repository.findById(customerId).orElseThrow(() ->
		new ResourceNotFoundException("No records found for this id!!"));
		
		customer = CustomerMapper.updateCustomer(customer, requestCustomerDTO);
		customer.setAddress(address.getAddressByViaCep(requestCustomerDTO));
		repository.save(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Customer data " + customer.getName() + " saved successfully!!");
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	@Override
	public Customer delete(UUID id) {
		customer = repository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("No records found for this id!!")
		);
		
		customer = CustomerMapper.customerDelete(customer);
		repository.save(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Customer " + customer.getName() + " deleted successfully!!");
		return customer;
	}

	@Override
	@Cacheable(cacheNames = "Customers", key = "#cep")
	public ListCustomer findByCep(String cep, Integer pageSize, Integer page, String sortBy) {
		Pageable pageable = paginationService.createPagination(pageSize, page, sortBy);
		
		pageListResponse = repository.findAllByAddressCepAndActiveTrue(cep, pageable);
		listResponse = new ArrayList<>();
		
		if(pageListResponse.isEmpty()) {
			LoggerConfig.LOGGER_CUSTOMER.error("No records found for this cep!!");
			throw new ResourceNotFoundException("No records found for this cep!!");
		}
		
		for (Customer customer : pageListResponse) {
			responseDTO = CustomerMapper.modelToResponseDTO(customer);
			listResponse.add(responseDTO);
		}
		
		response = paginationService.paginationCustomer(pageListResponse, listResponse);
		LoggerConfig.LOGGER_CUSTOMER.info(" List of Customers by cep successfully executed!! ");
		return response;
	
	}

	
	private String duplicateDocumentValidator(Customer customer) {
		String message = "";

		Customer customerEntityCpf = repository.findByCpf(customer.getCpf());

		if (customerEntityCpf != null) {
			message = "Cpf already registered";
		}
		return message;
	}

}
