package br.com.magnasistemas.apipassagem.dto.passagem;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record PassagemDtoComprar(
	

	LocalDateTime timestampCompra,

	@NotNull 
	Long idPassageiro
	
	) {

}
