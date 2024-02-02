package br.com.magnasistemas.apipassagem.dto.pais;

import br.com.magnasistemas.apipassagem.entity.Pais;

public record PaisDtoListagem(Long id,String nome,String sigla) {

	public PaisDtoListagem(Pais pais) {
		this(pais.getId(), pais.getNome(),pais.getSigla());
	}
}
