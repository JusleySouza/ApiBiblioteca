package br.com.library.test.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.library.model.Address;
import br.com.library.model.Customer;
import br.com.library.model.Employee;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.ListEmployee;
import br.com.library.model.dto.Pagination;
import br.com.library.model.dto.RequestCustomerDTO;
import br.com.library.model.dto.RequestEmployeeDTO;
import br.com.library.model.dto.ResponseAddressDTO;
import br.com.library.model.dto.ResponseCustomerDTO;
import br.com.library.model.dto.ResponseEmployeeDTO;
import br.com.library.model.dto.ViaCepDTO;

public final class ClassBuilder {

	public static Address addressBuilder() {
		Address address = new Address();
		address.setCity("São Paulo");
		address.setNeighborhood("Vila Emir");
		address.setNumber(56);
		address.setState("SP");
		address.setCep("04447-010");
		address.setStreet("Avenida Nossa Senhora do Sabará");
		address.setId(UUID.randomUUID());
		return address;
	}

	public static Customer customerBuilder() {
		Customer customer = new Customer();
		customer.setActive(true);
		customer.setChanged(LocalDate.now());
		customer.setCpf("12365478965");
		customer.setCreated(LocalDate.now());
		customer.setEmail("caio@castro.com");
		customer.setName("Caio Castro");
		customer.setPhone("1111111111");
		customer.setId(UUID.randomUUID());
		customer.setAddress(addressBuilder());
		return customer;
	}

	public static Employee employeeBuilder() {
		Employee employee = new Employee();
		employee.setActive(true);
		employee.setChanged(LocalDate.now());
		employee.setCpf("12365478965");
		employee.setCreated(LocalDate.now());
		employee.setEmail("caio@castro.com");
		employee.setName("Caio Castro");
		employee.setPhone("1111111111");
		employee.setPosition("Gerente");
		employee.setId(UUID.randomUUID());
		employee.setAddress(addressBuilder());
		return employee;
	}

	public static ResponseAddressDTO addressDTOBuilder() {
		ResponseAddressDTO responseAddressDTO = new ResponseAddressDTO();
		responseAddressDTO.setCity("São Paulo");
		responseAddressDTO.setNeighborhood("Vila Emir");
		responseAddressDTO.setNumber(56);
		responseAddressDTO.setStreet("Avenida Nossa Senhora do Sabará");
		responseAddressDTO.setCep("04447-010");
		responseAddressDTO.setState("SP");
		return responseAddressDTO;
	}

	public static RequestCustomerDTO requestCustomerDTOBuilder() {
		RequestCustomerDTO requestCustomerDTO = new RequestCustomerDTO();
		requestCustomerDTO.setCpf("12365478965");
		requestCustomerDTO.setEmail("caio@castro.com");
		requestCustomerDTO.setName("Caio Castro");
		requestCustomerDTO.setPhone("1111111111");
		requestCustomerDTO.setCep("04447-010");
		requestCustomerDTO.setNumber(56);
		return requestCustomerDTO;
	}
	
	public static RequestEmployeeDTO requestEmployeeDTOBuilder() {
		RequestEmployeeDTO requestEmployeeDTO = new RequestEmployeeDTO();
		requestEmployeeDTO.setCpf("12365478965");
		requestEmployeeDTO.setEmail("caio@castro.com");
		requestEmployeeDTO.setName("Caio Castro");
		requestEmployeeDTO.setPhone("1111111111");
		requestEmployeeDTO.setCep("04447-010");
		requestEmployeeDTO.setPosition("Gerente");
		requestEmployeeDTO.setNumber(56);
		return requestEmployeeDTO;
	}

	public static ResponseCustomerDTO responseCustomerDTOBuilder() {
		ResponseCustomerDTO responseCustomerDTO = new ResponseCustomerDTO();
		responseCustomerDTO.setName("Caio Castro");
		responseCustomerDTO.setId(UUID.randomUUID());
		responseCustomerDTO.setCpf("12365478965");
		responseCustomerDTO.setPhone("1111111111");
		responseCustomerDTO.setEmail("caio@castro.com");
		responseCustomerDTO.setResponseAddressDTO(addressDTOBuilder());
		return responseCustomerDTO;
	}
	
	public static ResponseEmployeeDTO responseEmployeeDTOBuilder() {
		ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO();
		responseEmployeeDTO.setName("Caio Castro");
		responseEmployeeDTO.setId(UUID.randomUUID());
		responseEmployeeDTO.setCpf("12365478965");
		responseEmployeeDTO.setPhone("1111111111");
		responseEmployeeDTO.setEmail("caio@castro.com");
		responseEmployeeDTO.setPosition("Gerente");
		responseEmployeeDTO.setResponseAddressDTO(addressDTOBuilder());
		return responseEmployeeDTO;
	}

	public static Pagination paginationBuilder() {
		Pagination pagination = new Pagination();
		pagination.setOffset(0);
		pagination.setPageSize(4);
		pagination.setPageNumber(1);
		pagination.setMoreElements(true);
		pagination.setTotalPages(6);
	    pagination.setTotalElements(2);
		return pagination;
	}
	
	public static ListCustomer listCustomerBuilder() {
		ListCustomer listCustomer = new ListCustomer();
		listCustomer.setPageable(paginationBuilder());
		List<ResponseCustomerDTO> listCustomerDTO = List.of(responseCustomerDTOBuilder());
		listCustomer.setContent(listCustomerDTO);
		return listCustomer;
	}
	
	public static ListEmployee listEmployeeBuilder() {
		ListEmployee listEmployee = new ListEmployee();
		listEmployee.setPageable(paginationBuilder());
		List<ResponseEmployeeDTO> listEmployeeDTO = List.of(responseEmployeeDTOBuilder());
		listEmployee.setContent(listEmployeeDTO);
		return listEmployee;
	}
	
	public static ViaCepDTO viaCepDTOBuilder() {
		ViaCepDTO viaCepDTO = new ViaCepDTO();
		viaCepDTO.setCep("04447-010");
		viaCepDTO.setLogradouro("Avenida Nossa Senhora do Sabará");
		viaCepDTO.setBairro("Vila Emir");
		viaCepDTO.setLocalidade("São Paulo");
		viaCepDTO.setUf("SP");
		viaCepDTO.setErro(false);
		return viaCepDTO;
	}
	
}
