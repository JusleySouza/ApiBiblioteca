package br.com.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.library.model.Address;
import br.com.library.model.dto.ViaCepDTO;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	@Mappings({
		@Mapping(target= "street", source  = "logradouro"),
		@Mapping(target= "neighborhood", source = "bairro"),
		@Mapping(target= "city", source = "localidade"),
		@Mapping(target= "state", source = "uf"),
		@Mapping(target= "id", ignore= true),
		@Mapping(target= "cep", ignore= true),
		@Mapping(target= "number", ignore= true)
	})
	Address toModel(ViaCepDTO viaCepDTO);

}
