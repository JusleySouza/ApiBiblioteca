package br.com.library.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ViaCepDTO {

	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private Boolean erro;

}
