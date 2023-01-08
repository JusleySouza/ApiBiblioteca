package br.com.library.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private AddressMapper mapper;
	
	private RestTemplate restTemplate;
	
	Address address = new Address();

	@Override
	public Address getAddressByViaCep(RequestDTO requestDTO) {
		restTemplate = new RestTemplate();
		ViaCepDTO viaCepDTO = restTemplate.getForObject(URL + requestDTO.getCep() + PATH, ViaCepDTO.class);
		
		if(viaCepDTO.getErro() != null) {
			throw new ResourceNotFoundException("Zip code not found");
		}
		
		address = mapper.toModel(viaCepDTO);
		address.setNumber(requestDTO.getNumber());
		address.setCep(requestDTO.getCep());
		
		LoggerConfig.LOGGER_ADDRESS.info("Address found successfully!!");
		
		return address;
	}

}
