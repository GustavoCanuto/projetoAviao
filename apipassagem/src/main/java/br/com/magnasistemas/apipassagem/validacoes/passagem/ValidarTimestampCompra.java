package br.com.magnasistemas.apipassagem.validacoes.passagem;

import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoCadastro;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class ValidarTimestampCompra implements ValidarCadastroPassagem  {

	public void validar(PassagemDtoCadastro dados) {
		if (dados.timestampCompra().isAfter(dados.timestampPartida())
				||dados.timestampCompra().isEqual(dados.timestampPartida())) {
			throw new ValidacaoException("A data/horário da compra deve ser antes da data/horário da partida");
		}
		
	}

}
