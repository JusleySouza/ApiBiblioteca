package br.com.library.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.library.config.LoggerConfig;
import br.com.library.exception.DuplicateDocumentsException;
import br.com.library.exception.ResourceNotFoundException;
import br.com.library.mapper.EmployeeMapper;
import br.com.library.model.Employee;
import br.com.library.model.dto.ListEmployee;
import br.com.library.model.dto.RequestEmployeeDTO;
import br.com.library.model.dto.ResponseEmployeeDTO;
import br.com.library.model.dto.error.ResponseError;
import br.com.library.repository.EmployeeRepository;
import br.com.library.services.AddressService;
import br.com.library.services.EmployeeService;
import br.com.library.services.PaginationService;

@Component
public class EmployeeServiceImplement implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private AddressService address;

	@Autowired
	private Validator validator;

	@Autowired
	private PaginationService paginationService;

	private Employee employee;
	private ResponseEmployeeDTO responseDTO;
	private Page<Employee> pageListResponse;
	private List<ResponseEmployeeDTO> listResponse;
	private ListEmployee response;

	@Override
	public ListEmployee findAll(Pageable pageable) {
		listResponse = new ArrayList<>();
		pageListResponse = repository.findAllByActiveTrue(pageable);

		for (Employee employee : pageListResponse) {
			responseDTO = EmployeeMapper.modelToResponseDTO(employee);
			listResponse.add(responseDTO);
		}

		response = paginationService.paginationEmployee(pageListResponse, listResponse);

		LoggerConfig.LOGGER_EMPLOYEE.info(" Employee List successfully executed!! ");

		return response;
	}

	@Override
	public ResponseEmployeeDTO findByCpf(String cpf) {
		employee = repository.findByCpf(cpf);

		if (employee == null) {
			LoggerConfig.LOGGER_EMPLOYEE.error("No records found for this cpf!!");
			throw new ResourceNotFoundException("No records found for this cpf!!");
		}

		responseDTO = EmployeeMapper.modelToResponseDTO(employee);
		LoggerConfig.LOGGER_EMPLOYEE.info("Employee found successfully!! ");
		return responseDTO;
	}

	@Override
	public ResponseEntity<Object> create(RequestEmployeeDTO requestEmployeeDTO) {
		Set<ConstraintViolation<RequestEmployeeDTO>> violations = validator.validate(requestEmployeeDTO);

		if (!violations.isEmpty()) {
			LoggerConfig.LOGGER_EMPLOYEE.error("Validation error");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		employee = EmployeeMapper.requestDTOToModel(requestEmployeeDTO);
		
		String message = duplicateDocumentValidator(employee);
		if(!message.isEmpty()) {
			LoggerConfig.LOGGER_EMPLOYEE.error("Duplicate documents");
			throw new DuplicateDocumentsException(message);
		}
		
		employee.setAddress(address.getAddressByViaCep(requestEmployeeDTO));
		repository.save(employee);
		LoggerConfig.LOGGER_EMPLOYEE.info("Employee " + employee.getName() + " saved successfully!!");
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Object> update(RequestEmployeeDTO requestEmployeeDTO, UUID employeeId) {
		Set<ConstraintViolation<RequestEmployeeDTO>> violations = validator.validate(requestEmployeeDTO);

		if (!violations.isEmpty()) {
			LoggerConfig.LOGGER_EMPLOYEE.error("Validation error");
			return new ResponseEntity<Object>(ResponseError.createFromValidations(violations),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		employee = repository.findById(employeeId).orElseThrow(() ->
		new ResourceNotFoundException("No records found for this id!!"));
		
		employee = EmployeeMapper.updateEmployee(employee, requestEmployeeDTO);
		employee.setAddress(address.getAddressByViaCep(requestEmployeeDTO));
		repository.save(employee);
		LoggerConfig.LOGGER_EMPLOYEE.info("Employee data " + employee.getName() + " saved successfully!!");
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	@Override
	public Employee delete(UUID id) {
		employee = repository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("No records found for this id!!")
		);
		
		employee = EmployeeMapper.employeeDelete(employee);
		repository.save(employee);
		LoggerConfig.LOGGER_EMPLOYEE.info("Employee " + employee.getName() + " deleted successfully!!");
		return employee;
	}

	@Override
	public ListEmployee findByCep(String cep, Pageable pageable) {
		pageListResponse = repository.findAllByAddressCepAndActiveTrue(cep, pageable);
		listResponse = new ArrayList<>();
		
		if(pageListResponse.isEmpty()) {
			LoggerConfig.LOGGER_EMPLOYEE.error("No records found for this cep!!");
			throw new ResourceNotFoundException("No records found for this cep!!");
		}
		
		for (Employee employee : pageListResponse) {
			responseDTO = EmployeeMapper.modelToResponseDTO(employee);
			listResponse.add(responseDTO);
		}
		
		response = paginationService.paginationEmployee(pageListResponse, listResponse);
		LoggerConfig.LOGGER_EMPLOYEE.info(" List of employees by cep successfully executed!! ");
		return response;
	}

	private String duplicateDocumentValidator(Employee employee) {
		String message = "";

		Employee employeeEntityCpf = repository.findByCpf(employee.getCpf());

		if (employeeEntityCpf != null) {
			message = "Cpf already registered";
		}
		return message;
	}

}
