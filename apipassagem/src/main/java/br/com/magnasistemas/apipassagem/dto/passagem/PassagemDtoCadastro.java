package br.com.magnasistemas.apipassagem.dto.passagem;

import java.time.LocalDateTime;

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
	Double valorPassagem
	
	
	) {

}
