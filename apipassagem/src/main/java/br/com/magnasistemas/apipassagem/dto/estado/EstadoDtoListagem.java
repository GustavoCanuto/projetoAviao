package br.com.magnasistemas.apipassagem.dto.estado;

import br.com.magnasistemas.apipassagem.dto.pais.PaisDtoListagem;
import br.com.magnasistemas.apipassagem.entity.Estado;

public class EstadoDtoListagem{
	Long id;
	String nome;
	String uf; 
	PaisDtoListagem pais;

	public EstadoDtoListagem(Estado estado) {
		this(estado.getId(), estado.getNome(),estado.getUf(), new PaisDtoListagem(estado.getPais()));
	}

	public EstadoDtoListagem(Long id, String nome, String uf, PaisDtoListagem pais) {
		this.id = id;
		this.nome = nome;
		this.uf = uf;
		this.pais = pais;
	}

	public Long id() {
		return id;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getUf() {
		return uf;
	}

	public PaisDtoListagem getPais() {
		return pais;
	}
	
}
