package br.com.magnasistemas.apipassagem.validacoes.passagem;

import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoCadastro;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class ValidarDestino implements ValidarCadastroPassagem {

	public void validar(PassagemDtoCadastro dados) {

		if (dados.idOrigem().equals(dados.idDestino())) {
			throw new ValidacaoException("O Aeroporto de destino tem que ser diferente do de origem");
		}
	}

}
