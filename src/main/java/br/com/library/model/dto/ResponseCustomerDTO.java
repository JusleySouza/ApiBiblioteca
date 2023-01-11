package br.com.library.model.dto;

import java.io.Serializable;
import java.util.UUID;

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
public class ResponseCustomerDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String name;
	private String cpf;
	private String phone;
	private String email;
	private ResponseAddressDTO responseAddressDTO;

}
