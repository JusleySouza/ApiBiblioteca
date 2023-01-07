package br.com.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.library.model.dto.RequestDTO;
import br.com.library.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService services;
	
	@GetMapping
	public ResponseEntity<List<Customer>> listCustomers(){
		return new ResponseEntity<List<Customer>>(services.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody RequestDTO requestDTO){
		return services.create(requestDTO);
	}
	
	@GetMapping("/{customerCpf}")
	public ResponseEntity<Customer> findByCpf(@PathVariable("customerCpf") String customerCpf){
		return new ResponseEntity<Customer>(services.findByCpf(customerCpf), HttpStatus.OK);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> update(@RequestBody RequestDTO requestDTO,
			@PathVariable("customerId") UUID customerId){
		return services.update(requestDTO, customerId);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Customer> delete(@PathVariable("customerId") UUID customerId){
		services.delete(customerId);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
	
}
