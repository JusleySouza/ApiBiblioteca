package br.com.library.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.library.model.Customer;
import br.com.library.model.Employee;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.ListEmployee;
import br.com.library.model.dto.ResponseCustomerDTO;
import br.com.library.model.dto.ResponseEmployeeDTO;

@Service
public interface PaginationService {

	public ListCustomer paginationCustomer(Page<Customer> pageListCustomer, List<ResponseCustomerDTO> customer);
	public ListEmployee paginationEmployee(Page<Employee> pageListEmployee, List<ResponseEmployeeDTO> employee);
	public Pageable createPagination(Integer pageSize, Integer page, String sortBy);
}
