package br.com.magnasistemas.apipassagem.dto.passagem;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class PassagemDtoComprar{
	

	LocalDateTime timestampCompra;

	@NotNull 
	Long idPassageiro;

	public PassagemDtoComprar(LocalDateTime timestampCompra, Long idPassageiro) {
		
		this.timestampCompra = timestampCompra;
		this.idPassageiro = idPassageiro;
	}

	public LocalDateTime timestampCompra() {
		return timestampCompra;
	}

	public Long idPassageiro() {
		return idPassageiro;
	}

	public LocalDateTime getTimestampCompra() {
		return timestampCompra;
	}

	public Long getIdPassageiro() {
		return idPassageiro;
	}
	
}
