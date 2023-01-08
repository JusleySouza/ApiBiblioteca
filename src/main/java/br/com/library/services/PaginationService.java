package br.com.library.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.library.model.Customer;
import br.com.library.model.dto.ListCustomer;
import br.com.library.model.dto.ResponseDTO;

@Service
public interface PaginationService {

	public ListCustomer pagination(Page<Customer> pageListCustomer, List<ResponseDTO> customer);
	
}
