package br.com.library.services.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.library.mapper.CustomerMapper;
import br.com.library.model.Customer;
import br.com.library.model.dto.RequestDTO;
import br.com.library.model.dto.ResponseDTO;
import br.com.library.repository.CustomerRepository;
import br.com.library.services.CustomerService;

@Component
public class CustomerServiceImplement implements CustomerService {
	
	@Autowired
	 private CustomerRepository repository;
	
	@Autowired
	private CustomerMapper mapper;
	
	private Customer customer;
	private ResponseDTO responseDTO;

	@Override
	public List<Customer> findAll() {
		List<Customer> listCustomer = repository.findAll();
		return listCustomer;
	}

	@Override
	public Customer findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Customer> create(RequestDTO requestCustomerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Customer> update(RequestDTO requestCustomerDTO, UUID customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer delete(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findByCep(String cep) {
		// TODO Auto-generated method stub
		return null;
	}

}
