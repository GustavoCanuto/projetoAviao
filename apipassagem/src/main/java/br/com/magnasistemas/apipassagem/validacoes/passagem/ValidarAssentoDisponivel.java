package br.com.magnasistemas.apipassagem.validacoes.passagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoCadastro;
import br.com.magnasistemas.apipassagem.enums.TipoAssento;
import br.com.magnasistemas.apipassagem.repository.AeronaveRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class ValidarAssentoDisponivel implements ValidarCadastroPassagem {

	@Autowired
	AeronaveRepository aeronaveRepository;

	public void validar(PassagemDtoCadastro dados) {

		var aeronave = aeronaveRepository.getReferenceById(dados.idAeronave());

		var qtdAssento = dados.tipo() == TipoAssento.ECONOMICO ? aeronave.getQtdAssentoEconomico()
				: aeronave.getQtdAssentoVip();

		if (qtdAssento < 1) {
			throw new ValidacaoException("Não há assentos disponvieis para essa categoria");
		} else {

			qtdAssento--;

			if (dados.tipo() == TipoAssento.ECONOMICO) aeronave.setQtdAssentoEconomico(qtdAssento);
			else aeronave.setQtdAssentoVip(qtdAssento);

			aeronaveRepository.save(aeronave);
		}
	}

}
