package br.com.magnasistemas.apipassagem.dto.aeronave;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AeronaveDtoCadastro(
		@NotNull
		 Long idCompanhia,
		 
		 @NotNull
		 @Size(max = 255)
		Integer qtdAssentoEconomico,
		 
		 @NotNull
		 @Size(max = 255)
		Integer qtdAssentoVip,

		 @NotBlank
		 @Size(max = 255)
		 String nsa,

		 @NotBlank
		 @Size(max = 255)
		 String modelo
			) {

}
