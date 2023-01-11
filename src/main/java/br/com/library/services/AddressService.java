package br.com.library.services;

import org.springframework.stereotype.Service;

import br.com.library.model.Address;
import br.com.library.model.dto.RequestCustomerDTO;
import br.com.library.model.dto.RequestEmployeeDTO;

@Service
public interface AddressService {
	
	public Address getAddressByViaCep(RequestCustomerDTO requestDTO);

	public Address getAddressByViaCep(RequestEmployeeDTO requestDTO);

}
