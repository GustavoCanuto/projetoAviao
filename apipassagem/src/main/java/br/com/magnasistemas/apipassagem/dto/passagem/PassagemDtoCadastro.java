package br.com.magnasistemas.apipassagem.dto.passagem;

import java.time.LocalDateTime;

import br.com.magnasistemas.apipassagem.enums.TipoAssento;
import jakarta.validation.constraints.NotNull;

public record PassagemDtoCadastro(
	
	@NotNull 
	LocalDateTime timestampCompra,
	
	@NotNull 
	LocalDateTime timestampPartida,

	@NotNull
	LocalDateTime timestampChegada,

	@NotNull 
	Long idOrigem,

	@NotNull 
	Long idDestino,
	
	@NotNull 
	Long idAeronave,

	@NotNull 
	Long idPassageiro,
	
	@NotNull 
	Double valorPassagem,
	
	@NotNull
	TipoAssento tipo
	
	
	) {

}
