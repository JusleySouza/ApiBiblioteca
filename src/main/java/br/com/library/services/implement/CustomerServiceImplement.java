package br.com.library.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.library.config.LoggerConfig;
import br.com.library.exception.DuplicateDocumentsException;
import br.com.library.exception.ResourceNotFoundException;
import br.com.library.mapper.CustomerMapper;
import br.com.library.mapper.UpdateModel;
import br.com.library.model.Customer;
import br.com.library.model.dto.RequestDTO;
import br.com.library.model.dto.ResponseDTO;
import br.com.library.model.dto.error.ResponseError;
import br.com.library.repository.CustomerRepository;
import br.com.library.services.AddressService;
import br.com.library.services.CustomerService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class CustomerServiceImplement implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private CustomerMapper mapper;

	@Autowired
	private AddressService address;

	@Autowired
	private Validator validator;

	private Customer customer;
	private ResponseDTO responseDTO;
	List<ResponseDTO> listResponse;

	@Override
	public List<ResponseDTO> findAll() {
		List<Customer> listCustomer = repository.findAllByActiveTrue();
		listResponse = new ArrayList<>();
		for (Customer customer : listCustomer) {
			responseDTO = mapper.modelToResponseCustomerDTO(customer);
			listResponse.add(responseDTO);
		}
		LoggerConfig.LOGGER_CUSTOMER.info(" Customer List successfully executed!! ");
		return listResponse;
	}

	@Override
	public ResponseDTO findByCpf(String cpf) {
		customer = repository.findByCpf(cpf);
		
		if(customer == null) {
			LoggerConfig.LOGGER_CUSTOMER.error("No records found for this cpf!!");
			throw new ResourceNotFoundException("No records found for this cpf!!");
		}
		
		responseDTO = mapper.modelToResponseCustomerDTO(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Customer found successfully!! ");
		return responseDTO;
	}

	@Override
	public ResponseEntity<Object> create(RequestDTO requestCustomerDTO) {
		Set<ConstraintViolation<RequestDTO>> violations = validator.validate(requestCustomerDTO);

		if (!violations.isEmpty()) {
			LoggerConfig.LOGGER_CUSTOMER.error("Validation error");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		customer = mapper.toModel(requestCustomerDTO);
		
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
	public ResponseEntity<Object> update(RequestDTO requestCustomerDTO, UUID customerId) {
		Set<ConstraintViolation<RequestDTO>> violations = validator.validate(requestCustomerDTO);

		if (!violations.isEmpty()) {
			LoggerConfig.LOGGER_CUSTOMER.error("Validation error");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		customer = repository.findById(customerId).orElseThrow(() ->
		new ResourceNotFoundException("No records found for this id!!"));
		
		customer = UpdateModel.customer(customer, requestCustomerDTO);
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
		
		customer = mapper.customerDelete(customer);
		repository.save(customer);
		LoggerConfig.LOGGER_CUSTOMER.info("Customer " + customer.getName() + " deleted successfully!!");
		return customer;
	}

	@Override
	public List<ResponseDTO> findByCep(String cep) {
		List<Customer> listCepCustomers = repository.findAllByAddressCepAndActiveTrue(cep);
		listResponse = new ArrayList<>();
		
		if(listCepCustomers.isEmpty()) {
			LoggerConfig.LOGGER_CUSTOMER.error("No records found for this cep!!");
			throw new ResourceNotFoundException("No records found for this cep!!");
		}
		
		for (Customer customer : listCepCustomers) {
			responseDTO = mapper.modelToResponseCustomerDTO(customer);
			listResponse.add(responseDTO);
		}
		LoggerConfig.LOGGER_CUSTOMER.info(" List of Customers by cep successfully executed!! ");
		return listResponse;
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
