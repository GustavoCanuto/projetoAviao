package br.com.magnasistemas.apipassagem.dto.aeroporto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AeroportoDtoCadastro
(

	
		@NotNull
		 Long idEndereco,

		 @NotBlank
		 @Size(max = 255)
		 String nome,

		 @NotBlank
		 @Email(message = "O email deve ser válido.")
		 String email
			

){


}