package br.com.library.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
	public Address create(RequestDTO requestDTO) {
		restTemplate = new RestTemplate();
		ViaCepDTO viaCepDTO = restTemplate.getForObject(URL + requestDTO.getCep() + PATH, ViaCepDTO.class);
		
		if(viaCepDTO.getErro() != null) {
			System.out.println("Aqui");
		}
		
		address = mapper.toModel(viaCepDTO);
		address.setNumber(requestDTO.getNumber());
		return address;
	}

}
