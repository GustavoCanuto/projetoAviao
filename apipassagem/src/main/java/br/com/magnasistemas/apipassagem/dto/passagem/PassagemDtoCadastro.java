package br.com.magnasistemas.apipassagem.dto.passagem;

import java.time.LocalDateTime;

import br.com.magnasistemas.apipassagem.enums.TipoAssento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
	@Positive(message = "O valor deve ser um valor positivo") 
	Double valorPassagem,
	
	@NotNull
	TipoAssento tipo
	
	
	) {

}
