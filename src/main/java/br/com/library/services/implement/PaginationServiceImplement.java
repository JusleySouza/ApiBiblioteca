package br.com.library.services.implement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.library.model.Customer;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.Pagination;
import br.com.library.model.dto.ResponseDTO;
import br.com.library.services.PaginationService;

@Component
public class PaginationServiceImplement implements PaginationService {
	private ListCustomer listCustomer;
	private Pagination pagination;

	@Override
	public ListCustomer pagination(Page<Customer> pageListCustomer, List<ResponseDTO> customer) {
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

}
