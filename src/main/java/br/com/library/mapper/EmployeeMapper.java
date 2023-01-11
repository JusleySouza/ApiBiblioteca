package br.com.library.mapper;

import java.time.LocalDate;

import br.com.library.model.Employee;
import br.com.library.model.dto.RequestEmployeeDTO;
import br.com.library.model.dto.ResponseEmployeeDTO;

public final class EmployeeMapper {
	
	public static Employee requestDTOToModel(RequestEmployeeDTO requestDTO) {
		return Employee.builder()
				.name(requestDTO.getName())
				.cpf(requestDTO.getCpf())
				.phone(requestDTO.getPhone())
				.email(requestDTO.getEmail())
				.position(requestDTO.getPosition())
				.created(LocalDate.now())
				.active(Boolean.TRUE)
				.build();
	}
	
	public static ResponseEmployeeDTO modelToResponseDTO(Employee employee) {
		return ResponseEmployeeDTO.builder()
				.id(employee.getId())
				.name(employee.getName())
				.cpf(employee.getCpf())
				.phone(employee.getPhone())
				.email(employee.getEmail())
				.position(employee.getPosition())
				.responseAddressDTO(AddressMapper.modelToResponseAddressDTO(employee))
				.build();
	}
	
	public static Employee employeeDelete(Employee employee) {
		employee.setActive(Boolean.FALSE);
		employee.setChanged(LocalDate.now());
		return employee;
	}
	
	public static Employee updateEmployee(Employee employee, RequestEmployeeDTO requestDTO) {
		return Employee.builder()
				.name(requestDTO.getName())
				.cpf(requestDTO.getCpf())
				.phone(requestDTO.getPhone())
				.email(requestDTO.getEmail())
				.position(requestDTO.getPosition())
				.changed(LocalDate.now())
				.id(employee.getId())
				.created(employee.getCreated())
				.active(employee.getActive())
				.build();
	}
	
}
