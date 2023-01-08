package br.com.library.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RequestDTO {

	@NotEmpty(message = "{name.not.empty}")
	private String name;
	@NotEmpty(message = "{cpf.not.empty}")
	private String cpf;
	@NotEmpty(message = "{phone.not.empty}")
	private String phone;
	@Email(message = "{email.not.valid}")
	@NotEmpty(message = "{email.not.empty}")
	private String email;
	@NotEmpty(message = "{cep.not.empty}")
	private String cep;
	@NotNull(message = "{number.not.null}")
	@Min(value=1, message = "{number.not.less.than}")
	private int number;
}
