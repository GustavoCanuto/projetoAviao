package br.com.magnasistemas.apipassagem.dto.aeronave;

import br.com.magnasistemas.apipassagem.entity.Aeronave;
import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;

public record AeronaveDtoDetalhar(
		
		 Long id,

		 CompanhiaAerea Companhia,
		 
		
		 Long qtdAssentoEconomico,
		 
	
		 Long qtdAssentoVip,


		 String nsa,

		 String modelo
)

{

	public AeronaveDtoDetalhar(Aeronave aeronave) {
		this(aeronave.getId(), aeronave.getCompanhia(), aeronave.getQtdAssentoEconomico(), 
				aeronave.getQtdAssentoVip(), aeronave.getNsa(),aeronave.getModelo());

	}
}
