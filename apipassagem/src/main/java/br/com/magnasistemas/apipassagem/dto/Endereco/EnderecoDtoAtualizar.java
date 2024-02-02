package br.com.magnasistemas.apipassagem.dto.Endereco;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDtoAtualizar(

		 Long idCidade,

		 @Size(max = 255)
		 String logradouro,

		 @Size(max = 20)
		 String complemento,
			
		 @Size(max = 8)
		 String numero,

		 @Pattern(regexp = "\\d{8}",  message = "O Cep deve ter 8 numeros.") 
		 String cep) {

}
