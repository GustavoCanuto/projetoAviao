package br.com.magnasistemas.apipassagem.dto.estado;

import br.com.magnasistemas.apipassagem.dto.pais.PaisDtoListagem;
import br.com.magnasistemas.apipassagem.entity.Estado;

public record EstadoDtoListagem(Long id,String nome,String uf, PaisDtoListagem pais) {

	public EstadoDtoListagem(Estado estado) {
		this(estado.getId(), estado.getNome(),estado.getUf(), new PaisDtoListagem(estado.getPais()));
	}
}
