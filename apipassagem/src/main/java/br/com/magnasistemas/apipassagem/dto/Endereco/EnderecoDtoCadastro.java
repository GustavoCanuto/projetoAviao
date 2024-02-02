package br.com.magnasistemas.apipassagem.dto.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDtoCadastro(

		@NotNull Long idCidade,

		@Size(max = 255)
		@NotBlank String logradouro,

		@Size(max = 20)
		String complemento,
		
		@Size(max = 8)
		@NotNull String numero,

		@NotNull @Pattern(regexp = "\\d{8}") String cep) {

}
