package br.com.library.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class RequestCustomerDTO {

	@NotEmpty(message = "{name.not.empty}")
	@Schema(description = "Employee name", type = "string", example = "Tiago Farias")
	private String name;
	@NotEmpty(message = "{cpf.not.empty}")
	@Schema(description = "Employee cpf", type = "string", example = "97531426809")
	private String cpf;
	@NotEmpty(message = "{phone.not.empty}")
	@Schema(description = "Employee phone", type = "string", example = "11985677685")
	private String phone;
	@Email(message = "{email.not.valid}")
	@NotEmpty(message = "{email.not.empty}")
	@Schema(description = "Employee email", type = "string", example = "tiago@farias.com")
	private String email;
	@NotEmpty(message = "{cep.not.empty}")
	@Schema(description = "Employee cep", type = "string", example = "04777060")
	private String cep;
	@NotNull(message = "{number.not.null}")
	@Min(value=1, message = "{number.not.less.than}")
	@Schema(description = "Employee number", type = "string", example = "50")
	private int number;
}
