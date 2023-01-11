package br.com.library.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAddressDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cep;
	private int number;
	private String street;
	private String neighborhood;
	private String city;
	private String state;
	
}
