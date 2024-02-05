package br.com.magnasistemas.apipassagem.dto.passagem;

import java.time.LocalDateTime;

import br.com.magnasistemas.apipassagem.entity.Aeronave;
import br.com.magnasistemas.apipassagem.entity.Aeroporto;
import br.com.magnasistemas.apipassagem.entity.Passageiro;
import br.com.magnasistemas.apipassagem.entity.Passagem;
import br.com.magnasistemas.apipassagem.enums.TipoAssento;
import jakarta.validation.constraints.Positive;

public record PassagemDtoDetalhar(
		
		Long id,
	
		LocalDateTime timestampCompra,
		
	
		LocalDateTime timestampPartida,


		LocalDateTime timestampChegada,


		Aeroporto idOrigem,

		
		Aeroporto  idDestino,
		
	
		Aeronave idAeronave,

		
		Passageiro idPassageiro,
		
		@Positive(message = "O valor deve ser um valor positivo") 
		Double valorPassagem,
		
		TipoAssento tipo
		) {

	public PassagemDtoDetalhar(Passagem passagem) {
		this(passagem.getId(), passagem.getTimestampCompra(), 
				passagem.getTimestampPartida(), passagem.getTimestampChegada(),passagem.getIdOrigem(),passagem.getIdDestino(),
				passagem.getIdAeronave(),passagem.getIdPassageiro(),passagem.getValorPassagem(), passagem.getTipoAssento());
	}
	


	
}
