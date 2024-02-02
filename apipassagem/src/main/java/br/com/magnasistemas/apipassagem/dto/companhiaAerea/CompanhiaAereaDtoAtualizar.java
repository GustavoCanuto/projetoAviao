package br.com.magnasistemas.apipassagem.dto.companhiaAerea;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanhiaAereaDtoAtualizar(

		@Size(max = 255)
		String nome,

		@Pattern(regexp = "\\d{11}", message = "O CNPJ deve conter 11 numeros.") String cnpj,

		@Email(message = "O email deve ser válido.") String email) {

}
