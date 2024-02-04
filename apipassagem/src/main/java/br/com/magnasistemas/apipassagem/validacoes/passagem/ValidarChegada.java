package br.com.magnasistemas.apipassagem.validacoes.passagem;

import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoCadastro;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class ValidarChegada implements ValidarCadastroPassagem {

	public void validar(PassagemDtoCadastro dados) {

		if (dados.timestampChegada().isBefore(dados.timestampPartida())||dados.timestampChegada().isEqual(dados.timestampPartida())) {
			throw new ValidacaoException("A chegada prevista deve ser em uma data/horário após a partida");
		}
	}

}
