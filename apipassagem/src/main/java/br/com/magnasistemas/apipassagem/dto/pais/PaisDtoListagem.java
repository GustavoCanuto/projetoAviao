package br.com.magnasistemas.apipassagem.dto.pais;

import br.com.magnasistemas.apipassagem.entity.Pais;

public class PaisDtoListagem {
	Long id;
	String nome;
	String sigla;

	public PaisDtoListagem(Pais pais) {
		this(pais.getId(), pais.getNome(), pais.getSigla());
	}

	public PaisDtoListagem(Long id, String nome, String sigla) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
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

	public String getSigla() {
		return sigla;
	}

}
