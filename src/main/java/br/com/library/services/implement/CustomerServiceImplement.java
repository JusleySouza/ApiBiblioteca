package br.com.library.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.library.mapper.CustomerMapper;
import br.com.library.mapper.UpdateModel;
import br.com.library.model.Customer;
import br.com.library.model.dto.RequestDTO;
import br.com.library.model.dto.ResponseDTO;
import br.com.library.repository.CustomerRepository;
import br.com.library.services.AddressService;
import br.com.library.services.CustomerService;

@Component
public class CustomerServiceImplement implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private CustomerMapper mapper;
	
	@Autowired
	private AddressService address;

	private Customer customer;
	private ResponseDTO responseDTO;
	List<ResponseDTO> listResponse;

	@Override
	public List<ResponseDTO> findAll() {
		List<Customer> listCustomer = repository.findAllByActiveTrue();
		listResponse = new ArrayList<>();
		for (Customer customer : listCustomer) {
			responseDTO =	mapper.modelToResponseCustomerDTO(customer);
			listResponse.add(responseDTO);
		}
		return listResponse;
	}

	@Override
	public Customer findByCpf(String cpf) {
		customer = repository.findByCpf(cpf);
		return customer;
	}

	@Override
	public ResponseEntity<Customer> create(RequestDTO requestCustomerDTO) {
		customer = mapper.toModel(requestCustomerDTO);
		customer.setAddress(address.create(requestCustomerDTO));
		repository.save(customer);
		return new ResponseEntity<Customer>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Customer> update(RequestDTO requestDTO, UUID customerId) {
		customer = repository.findById(customerId).get();
		customer = UpdateModel.customer(customer, requestDTO);
		repository.save(customer);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

	@Override
	public Customer delete(UUID id) {
		customer = repository.findById(id).get();
		customer = mapper.customerDelete(customer);
		repository.save(customer);
		return customer;
	}

	@Override
	public List<Customer> findByCep(String cep) {
		// TODO Auto-generated method stub
		return null;
	}

}