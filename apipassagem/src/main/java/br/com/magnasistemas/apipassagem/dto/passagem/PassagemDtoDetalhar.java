package br.com.magnasistemas.apipassagem.dto.passagem;

import java.time.LocalDateTime;

import br.com.magnasistemas.apipassagem.entity.Aeronave;
import br.com.magnasistemas.apipassagem.entity.Aeroporto;
import br.com.magnasistemas.apipassagem.entity.Passageiro;
import br.com.magnasistemas.apipassagem.entity.Passagem;
import br.com.magnasistemas.apipassagem.enums.TipoAssento;

public class PassagemDtoDetalhar{
		
		Long id;
	
		LocalDateTime timestampCompra;
		
	
		LocalDateTime timestampPartida;


		LocalDateTime timestampChegada;


		Aeroporto idOrigem;

		
		Aeroporto  idDestino;
		
	
		Aeronave idAeronave;


		Passageiro idPassageiro;
		
		Double valorPassagem;
		
		TipoAssento tipo;
	

	public PassagemDtoDetalhar(Passagem passagem) {
		this(passagem.getId(), passagem.getTimestampCompra(), 
				passagem.getTimestampPartida(), passagem.getTimestampChegada(),passagem.getIdOrigem(),passagem.getIdDestino(),
				passagem.getIdAeronave(),passagem.getIdPassageiro(),passagem.getValorPassagem(), passagem.getTipoAssento());
	}


	public PassagemDtoDetalhar(Long id, LocalDateTime timestampCompra, LocalDateTime timestampPartida,
			LocalDateTime timestampChegada, Aeroporto idOrigem, Aeroporto idDestino, Aeronave idAeronave,
			Passageiro idPassageiro,  Double valorPassagem,
			TipoAssento tipo) {

		this.id = id;
		this.timestampCompra = timestampCompra;
		this.timestampPartida = timestampPartida;
		this.timestampChegada = timestampChegada;
		this.idOrigem = idOrigem;
		this.idDestino = idDestino;
		this.idAeronave = idAeronave;
		this.idPassageiro = idPassageiro;
		this.valorPassagem = valorPassagem;
		this.tipo = tipo;
	}

	
	public Long id() {
		return id;
	}
	public Long getId() {
		return id;
	}

	public LocalDateTime getTimestampCompra() {
		return timestampCompra;
	}

	public LocalDateTime getTimestampPartida() {
		return timestampPartida;
	}

	public LocalDateTime getTimestampChegada() {
		return timestampChegada;
	}

	public Aeroporto getIdOrigem() {
		return idOrigem;
	}

	public Aeroporto getIdDestino() {
		return idDestino;
	}

	public Aeronave getIdAeronave() {
		return idAeronave;
	}

	public Passageiro getIdPassageiro() {
		return idPassageiro;
	}

	public Double getValorPassagem() {
		return valorPassagem;
	}

	public TipoAssento getTipo() {
		return tipo;
	}

}
