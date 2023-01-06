package br.com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.library.model.Customer;
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

}
