package br.com.magnasistemas.apipassagem.dto.passagem;

import java.time.LocalDateTime;

import br.com.magnasistemas.apipassagem.enums.TipoAssento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PassagemDtoCadastro{
	
	
	@NotNull 
	LocalDateTime timestampPartida;

	@NotNull
	LocalDateTime timestampChegada;

	@NotNull 
	Long idOrigem;

	@NotNull 
	Long idDestino;
	
	@NotNull 
	Long idAeronave;
	
	@NotNull 
	@Positive(message = "O valor deve ser um valor positivo") 
	Double valorPassagem;
	
	@NotNull
	TipoAssento tipo;

	public PassagemDtoCadastro(LocalDateTime timestampPartida, LocalDateTime timestampChegada,
			Long idOrigem, Long idDestino,Long idAeronave,
			Double valorPassagem,
			TipoAssento tipo) {
		
		this.timestampPartida = timestampPartida;
		this.timestampChegada = timestampChegada;
		this.idOrigem = idOrigem;
		this.idDestino = idDestino;
		this.idAeronave = idAeronave;
		this.valorPassagem = valorPassagem;
		this.tipo = tipo;
	}

	public LocalDateTime timestampPartida() {
		return timestampPartida;
	}

	public LocalDateTime timestampChegada() {
		return timestampChegada;
	}

	public Long idOrigem() {
		return idOrigem;
	}

	public Long idDestino() {
		return idDestino;
	}

	public Long idAeronave() {
		return idAeronave;
	}

	public Double valorPassagem() {
		return valorPassagem;
	}

	public TipoAssento tipo() {
		return tipo;
	}

	public LocalDateTime getTimestampPartida() {
		return timestampPartida;
	}

	public LocalDateTime getTimestampChegada() {
		return timestampChegada;
	}

	public Long getIdOrigem() {
		return idOrigem;
	}

	public Long getIdDestino() {
		return idDestino;
	}

	public Long getIdAeronave() {
		return idAeronave;
	}

	public Double getValorPassagem() {
		return valorPassagem;
	}

	public TipoAssento getTipo() {
		return tipo;
	}
	
}
