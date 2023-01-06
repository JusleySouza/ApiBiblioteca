package br.com.library.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseAddressDTO {

	private String cep;
	private int number;
	private String street;
	private String neighborhood;
	private String city;
	private String uf;
	
}
