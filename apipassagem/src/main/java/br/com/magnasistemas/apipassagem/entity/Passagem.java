package br.com.magnasistemas.apipassagem.entity;

import java.time.LocalDateTime;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoCadastro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "tb_passagem")
@Entity(name = "Passagem")
public class Passagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime timestampCompra;
	
	private LocalDateTime timestampPartida;

	private LocalDateTime timestampChegada;
	
	private Double valorPassagem;

	@ManyToOne
	@JoinColumn(name = "fk_origem")
	private Aeroporto idOrigem;

	@ManyToOne
	@JoinColumn(name = "fk_destino")
	private Aeroporto idDestino;

	@ManyToOne
	@JoinColumn(name = " fk_aeronave")
	private Aeronave idAeronave;
	
	@ManyToOne
	@JoinColumn(name = "fk_passageiro")
	private Passageiro idPassageiro;

	public Passagem(PassagemDtoCadastro dados,Aeroporto idOrigem,Aeroporto idDestino,Aeronave idAeronave,Passageiro idPassageiro) {
	
		this.timestampCompra = dados.timestampCompra();
		this.timestampPartida = dados.timestampPartida();
		this.timestampChegada = dados.timestampChegada();
		this.idOrigem = idOrigem;
		this.idDestino = idDestino;
		this.idAeronave = idAeronave;
		this.idPassageiro = idPassageiro;
		this.valorPassagem = dados.valorPassagem();
	}

	public Passagem(LocalDateTime timestampCompra, LocalDateTime timestampPartida,
			LocalDateTime timestampChegada, Aeroporto idOrigem, Aeroporto idDestino, Aeronave idAeronave,
			Passageiro idPassageiro, Double valorPassagem) {

		this.timestampCompra = timestampCompra;
		this.timestampPartida = timestampPartida;
		this.timestampChegada = timestampChegada;
		this.idOrigem = idOrigem;
		this.idDestino = idDestino;
		this.idAeronave = idAeronave;
		this.idPassageiro = idPassageiro;
		this.valorPassagem = valorPassagem;
	}

	public Passagem() {
	
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



	public Double getValorPassagem() {
		return valorPassagem;
	}


}
