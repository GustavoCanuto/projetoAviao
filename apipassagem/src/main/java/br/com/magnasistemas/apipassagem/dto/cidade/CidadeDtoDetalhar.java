package br.com.magnasistemas.apipassagem.dto.cidade;

import br.com.magnasistemas.apipassagem.dto.estado.EstadoDtoListagem;
import br.com.magnasistemas.apipassagem.entity.Cidade;

public record CidadeDtoDetalhar(Long id,String nome, EstadoDtoListagem estado) {
	
	public CidadeDtoDetalhar(Cidade cidade) {
		this(cidade.getId(), cidade.getNome(), new EstadoDtoListagem(cidade.getEstado()));
	}
	

}
