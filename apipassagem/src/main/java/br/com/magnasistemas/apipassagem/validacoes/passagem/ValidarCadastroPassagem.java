package br.com.magnasistemas.apipassagem.validacoes.passagem;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoCadastro;

public interface ValidarCadastroPassagem {

	void validar(PassagemDtoCadastro dados);
	
}
