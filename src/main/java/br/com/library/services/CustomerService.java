package br.com.library.services;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.library.model.Customer;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.RequestCustomerDTO;
import br.com.library.model.dto.ResponseCustomerDTO;


@Service
public interface CustomerService {
	
	public ListCustomer findAll(Pageable pageable);
	
	public ResponseCustomerDTO findByCpf(String cpf);

	public ResponseEntity<Object> create(RequestCustomerDTO requestCustomerDTO);

	public ResponseEntity<Object> update(RequestCustomerDTO requestCustomerDTO, UUID customerId);

	public Customer delete(UUID id);
	
	public ListCustomer findByCep(String cep, Pageable pageable);

}
