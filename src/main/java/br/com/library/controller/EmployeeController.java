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

import br.com.library.model.Employee;
import br.com.library.model.dto.ListEmployee;
import br.com.library.model.dto.RequestEmployeeDTO;
import br.com.library.model.dto.ResponseEmployeeDTO;
import br.com.library.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService services;
	
	@GetMapping
	public ResponseEntity<ListEmployee> listEmployees(
			@Min(value=1, message = "Tamanho mínimo 1.")
			@RequestParam(defaultValue = "10" , value="pageSize", required = false) Integer pageSize, 
			@Min(value=0, message = "Tamanho mínimo 0.")
			@RequestParam(defaultValue = "0" , value="page", required = false) Integer page, 
			@RequestParam(defaultValue = "name, DESC" , value="sortBy", required = false) String sortBy){
		return new ResponseEntity<ListEmployee>(services.findAll(pageSize, page, sortBy), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody RequestEmployeeDTO requestDTO){
		return services.create(requestDTO);
	}
	
	@GetMapping("/{employeeCpf}")
	public ResponseEntity<ResponseEmployeeDTO> findByCpf(@PathVariable("employeeCpf") String employeeCpf){
		return new ResponseEntity<ResponseEmployeeDTO>(services.findByCpf(employeeCpf), HttpStatus.OK);
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<Object> update(@RequestBody RequestEmployeeDTO requestDTO,
			@PathVariable("employeeId") UUID employeeId){
		return services.update(requestDTO, employeeId);
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<Employee> delete(@PathVariable("employeeId") UUID employeeId){
		services.delete(employeeId);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/cep/{employeeCep}")
	public ResponseEntity<ListEmployee> findByCep(@PathVariable("employeeCep") String employeeCep, 
			@Min(value=1, message = "Tamanho mínimo 1.")
			@RequestParam(defaultValue = "10" , value="pageSize", required = false) Integer pageSize, 
			@Min(value=0, message = "Tamanho mínimo 0.")
			@RequestParam(defaultValue = "0" , value="page", required = false) Integer page, 
			@RequestParam(defaultValue = "name, DESC" , value="sortBy", required = false) String sortBy){
		return new ResponseEntity<ListEmployee>(services.findByCep(employeeCep, pageSize, page, sortBy), HttpStatus.OK);
	}

}
