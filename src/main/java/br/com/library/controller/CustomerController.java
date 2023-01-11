package br.com.library.controller;

import java.util.UUID;

import javax.validation.constraints.Min;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.library.model.Customer;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.RequestCustomerDTO;
import br.com.library.model.dto.ResponseCustomerDTO;
import br.com.library.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService services;
	
	@GetMapping
	public ResponseEntity<ListCustomer> listCustomers(
			@Min(value=1, message = "Tamanho mínimo 1.")
			@RequestParam(defaultValue = "10" , value="pageSize", required = false) Integer pageSize, 
			@Min(value=0, message = "Tamanho mínimo 0.")
			@RequestParam(defaultValue = "0" , value="page", required = false) Integer page, 
			@RequestParam(defaultValue = "name, DESC" , value="sortBy", required = false) String sortBy){
		return new ResponseEntity<ListCustomer>(services.findAll(pageSize, page, sortBy), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RequestCustomerDTO requestDTO){
		return services.create(requestDTO);
	}
	
	@GetMapping("/{customerCpf}")
	public ResponseEntity<ResponseCustomerDTO> findByCpf(@PathVariable("customerCpf") String customerCpf){
		return new ResponseEntity<ResponseCustomerDTO>(services.findByCpf(customerCpf), HttpStatus.OK);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Object> update(@RequestBody RequestCustomerDTO requestDTO,
			@PathVariable("customerId") UUID customerId){
		return services.update(requestDTO, customerId);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Customer> delete(@PathVariable("customerId") UUID customerId){
		services.delete(customerId);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/cep/{customerCep}")
	public ResponseEntity<ListCustomer> findByCep(@PathVariable("customerCep") String customerCep,
			@Min(value=1, message = "Tamanho mínimo 1.")
			@RequestParam(defaultValue = "10" , value="pageSize", required = false) Integer pageSize, 
			@Min(value=0, message = "Tamanho mínimo 0.")
			@RequestParam(defaultValue = "0" , value="page", required = false) Integer page, 
			@RequestParam(defaultValue = "name, DESC" , value="sortBy", required = false) String sortBy){
		return new ResponseEntity<ListCustomer>(services.findByCep(customerCep, pageSize, page, sortBy), HttpStatus.OK);
	}
	
}
