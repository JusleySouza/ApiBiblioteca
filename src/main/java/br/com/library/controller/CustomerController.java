package br.com.library.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.library.model.Customer;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.RequestDTO;
import br.com.library.model.dto.ResponseDTO;
import br.com.library.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService services;
	
	@GetMapping
	public ResponseEntity<ListCustomer> listCustomers(Pageable pageable){
		return new ResponseEntity<ListCustomer>(services.findAll(pageable), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RequestDTO requestDTO){
		return services.create(requestDTO);
	}
	
	@GetMapping("/{customerCpf}")
	public ResponseEntity<ResponseDTO> findByCpf(@PathVariable("customerCpf") String customerCpf){
		return new ResponseEntity<ResponseDTO>(services.findByCpf(customerCpf), HttpStatus.OK);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Object> update(@RequestBody RequestDTO requestDTO,
			@PathVariable("customerId") UUID customerId){
		return services.update(requestDTO, customerId);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Customer> delete(@PathVariable("customerId") UUID customerId){
		services.delete(customerId);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/cep/{customerCep}")
	public ResponseEntity<ListCustomer> findByCep(@PathVariable("customerCep") String customerCep, Pageable pageable ){
		return new ResponseEntity<ListCustomer>(services.findByCep(customerCep, pageable), HttpStatus.OK);
	}
	
}
