package br.com.library.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.library.model.Employee;
import br.com.library.model.dto.ListEmployee;
import br.com.library.model.dto.RequestEmployeeDTO;
import br.com.library.model.dto.ResponseEmployeeDTO;

@Service
public interface EmployeeService {

	public ListEmployee findAll(Integer pageSize, Integer page, String sortBy);

	public ResponseEmployeeDTO findByCpf(String cpf);

	public ResponseEntity<Object> create(RequestEmployeeDTO requestEmployeeDTO);

	public ResponseEntity<Object> update(RequestEmployeeDTO requestEmployeeDTO, UUID employeeId);

	public Employee delete(UUID id);

	public ListEmployee findByCep(String cep, Integer pageSize, Integer page, String sortBy);

}
