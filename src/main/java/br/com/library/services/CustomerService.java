package br.com.library.services;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.library.model.Customer;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.RequestDTO;
import br.com.library.model.dto.ResponseDTO;


@Service
public interface CustomerService {
	
	public ListCustomer findAll(Pageable pageable);
	
	public ResponseDTO findByCpf(String cpf);

	public ResponseEntity<Object> create(RequestDTO requestCustomerDTO);

	public ResponseEntity<Object> update(RequestDTO requestCustomerDTO, UUID customerId);

	public Customer delete(UUID id);
	
	public ListCustomer findByCep(String cep, Pageable pageable);

}
