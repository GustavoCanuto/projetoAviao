package br.com.magnasistemas.apipassagem.dto.aeronave;


import jakarta.validation.constraints.Size;

public record AeronaveDtoAtualizar(
		
		 Long idCompanhia,
		 
		 @Size(max = 255)
		 Integer qtdAssentoEconomico,
		 
		 
		 @Size(max = 255)
		 Integer qtdAssentoVip

		
		 ) {

}
