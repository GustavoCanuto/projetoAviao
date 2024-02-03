package br.com.magnasistemas.apipassagem.dto.companhia;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanhiaAereaDtoCadastro(
	 @NotBlank
	 @Size(max = 255)
	 String nome,
	 

	@NotNull
	@Pattern(regexp = "\\d{11}", message = "O CNPJ deve conter 11 numeros.")
	String cnpj,

	 @NotBlank
	 @Email(message = "O email deve ser válido.")
	 String email
	 ) {
		
}
