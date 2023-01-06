package br.com.library.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.library.model.Customer;
import br.com.library.model.dto.RequestDTO;


@Service
public interface CustomerService {
	
	public List<Customer> findAll();
	
	public Customer findByCpf(String cpf);

	public ResponseEntity<Customer> create(RequestDTO requestCustomerDTO);

	public ResponseEntity<Customer> update(RequestDTO requestCustomerDTO, UUID customerId);

	public Customer delete(UUID id);
	
	public List<Customer> findByCep(String cep);

}
