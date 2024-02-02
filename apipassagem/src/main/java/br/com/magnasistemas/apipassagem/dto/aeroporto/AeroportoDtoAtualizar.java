package br.com.magnasistemas.apipassagem.dto.aeroporto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record AeroportoDtoAtualizar 
	(

			 Long idEndereco,

			 @Size(max = 255)
			 String nome,

			 @Email(message = "O email deve ser válido.")
			 String email
				
	
){

	
}
