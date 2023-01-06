package br.com.library.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.com.library.model.Customer;
import br.com.library.model.dto.RequestDTO;
import br.com.library.model.dto.ResponseDTO;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	@Mappings({
		@Mapping(target= "created", expression = "java(java.time.LocalDate.now())"),
		@Mapping(target= "active", expression = "java(java.lang.Boolean.TRUE)"),
		@Mapping(target= "id", ignore= true),
		@Mapping(target= "changed", ignore= true),
		@Mapping(target= "address", ignore= true),
	})
	Customer toModel(RequestDTO requestCustomerDTO);
	
	@Mappings({
		@Mapping(target= "changed", expression = "java(java.time.LocalDate.now())"),
		@Mapping(target= "active", expression = "java(java.lang.Boolean.FALSE)"),
	})
	Customer customerDelete(Customer customer);
	
	ResponseDTO modelToResponseCustomerDTO(Customer customer);
	
}
