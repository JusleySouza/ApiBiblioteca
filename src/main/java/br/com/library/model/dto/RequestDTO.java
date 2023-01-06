package br.com.library.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RequestDTO {

	private String name;
	private String cpf;
	private String phone;
	private String email;
	private String cep;
	private int number;
}
