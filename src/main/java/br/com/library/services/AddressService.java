package br.com.library.services;

import org.springframework.stereotype.Service;

import br.com.library.model.Address;
import br.com.library.model.dto.RequestDTO;

@Service
public interface AddressService {
	
	public Address create(RequestDTO requestDTO);

}
