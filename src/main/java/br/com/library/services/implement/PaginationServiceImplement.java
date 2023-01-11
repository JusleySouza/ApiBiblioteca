package br.com.library.services.implement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.library.model.Customer;
import br.com.library.model.Employee;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.ListEmployee;
import br.com.library.model.dto.Pagination;
import br.com.library.model.dto.ResponseCustomerDTO;
import br.com.library.model.dto.ResponseEmployeeDTO;
import br.com.library.services.PaginationService;

@Component
public class PaginationServiceImplement implements PaginationService {
	private ListCustomer listCustomer;
	private ListEmployee listEmployee;
	private Pagination pagination;

	@Override
	public ListCustomer paginationCustomer(Page<Customer> pageListCustomer, List<ResponseCustomerDTO> customer) {
		pagination = new Pagination();
		listCustomer = new ListCustomer();

		listCustomer.setContent(customer);
		
		pagination.setMoreElements(!pageListCustomer.isLast());
		pagination.setOffset(pageListCustomer.getPageable().getOffset());
        pagination.setPageNumber(pageListCustomer.getPageable().getPageNumber());
        pagination.setPageSize(pageListCustomer.getPageable().getPageSize());
        pagination.setTotalElements(pageListCustomer.getTotalElements());
        pagination.setTotalPages(pageListCustomer.getTotalPages());
        
        listCustomer.setPageable(pagination);
		
		return listCustomer;
	}

	@Override
	public ListEmployee paginationEmployee(Page<Employee> pageListEmployee, List<ResponseEmployeeDTO> employee) {

		pagination = new Pagination();
		listEmployee = new ListEmployee();

		listEmployee.setContent(employee);
		
		pagination.setMoreElements(!pageListEmployee.isLast());
		pagination.setOffset(pageListEmployee.getPageable().getOffset());
        pagination.setPageNumber(pageListEmployee.getPageable().getPageNumber());
        pagination.setPageSize(pageListEmployee.getPageable().getPageSize());
        pagination.setTotalElements(pageListEmployee.getTotalElements());
        pagination.setTotalPages(pageListEmployee.getTotalPages());
        
        listEmployee.setPageable(pagination);
		
		return listEmployee;
	}

}
