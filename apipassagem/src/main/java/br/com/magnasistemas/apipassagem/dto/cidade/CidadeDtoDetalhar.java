package br.com.magnasistemas.apipassagem.dto.cidade;

import br.com.magnasistemas.apipassagem.dto.estado.EstadoDtoListagem;
import br.com.magnasistemas.apipassagem.entity.Cidade;

public class CidadeDtoDetalhar{
	Long id;
	String nome;
	EstadoDtoListagem estado;
	
	public CidadeDtoDetalhar(Cidade cidade) {
		this(cidade.getId(), cidade.getNome(), new EstadoDtoListagem(cidade.getEstado()));
	}

	public CidadeDtoDetalhar(Long id, String nome, EstadoDtoListagem estado) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
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

	public EstadoDtoListagem getEstado() {
		return estado;
	}
	
}
