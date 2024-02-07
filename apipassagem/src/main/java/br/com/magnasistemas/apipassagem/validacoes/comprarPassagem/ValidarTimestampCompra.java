package br.com.magnasistemas.apipassagem.validacoes.comprarPassagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoComprar;
import br.com.magnasistemas.apipassagem.repository.PassagemRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class ValidarTimestampCompra implements ValidarCompraPassagem {

	@Autowired
	private PassagemRepository passagemRepository;

	public void validar(PassagemDtoComprar dados, Long id) {

		var passagem = passagemRepository.getReferenceById(id);

		if (dados.timestampCompra().isAfter(passagem.getTimestampPartida())
				|| dados.timestampCompra().isEqual(passagem.getTimestampPartida())) {
			throw new ValidacaoException("A data/horário da compra deve ser antes da data/horário da partida");
		}

	}

}
