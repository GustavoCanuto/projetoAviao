package br.com.magnasistemas.apipassagem.validacoes.comprarPassagem;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoComprar;

public interface ValidarCompraPassagem {

	void validar(PassagemDtoComprar dados, Long id);
	
}
