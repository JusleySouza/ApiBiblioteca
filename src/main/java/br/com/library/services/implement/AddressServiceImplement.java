package br.com.library.services.implement;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.library.config.LoggerConfig;
import br.com.library.exception.ResourceNotFoundException;
import br.com.library.mapper.AddressMapper;
import br.com.library.model.Address;
import br.com.library.model.dto.RequestDTO;
import br.com.library.model.dto.ViaCepDTO;
import br.com.library.services.AddressService;

@Component
public class AddressServiceImplement implements AddressService {
	
	private final String URL = "https://viacep.com.br/ws/";
	private final String PATH = "/json/";
	
	private RestTemplate restTemplate;
	
	Address address = new Address();

	@Override
	public Address getAddressByViaCep(RequestDTO requestDTO) {
		restTemplate = new RestTemplate();
		ViaCepDTO viaCepDTO = restTemplate.getForObject(URL + requestDTO.getCep() + PATH, ViaCepDTO.class);
		
		if(viaCepDTO.getErro() != null) {
			LoggerConfig.LOGGER_ADDRESS.info("Cep not found!!");
			throw new ResourceNotFoundException("Cep not found");
		}
		
		address = AddressMapper.viaCepToModel(viaCepDTO, requestDTO);
		address.setNumber(requestDTO.getNumber());
		address.setCep(requestDTO.getCep());
		
		LoggerConfig.LOGGER_ADDRESS.info("Address found successfully!!");
		
		return address;
	}

}
