package br.com.library.model.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseDTO {
	
	private UUID id;
	private String name;
	private String cpf;
	private String phone;
	private String email;
	private ResponseAddressDTO responseAddressDTO;

}
