package br.com.magnasistemas.apipassagem.validacoes.comprarPassagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoComprar;
import br.com.magnasistemas.apipassagem.enums.TipoAssento;
import br.com.magnasistemas.apipassagem.repository.AeronaveRepository;
import br.com.magnasistemas.apipassagem.repository.PassagemRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class ValidarAssentoDisponivel implements ValidarCompraPassagem {

	@Autowired
	AeronaveRepository aeronaveRepository;
	
	@Autowired
	private PassagemRepository passagemRepository;

	public void validar(PassagemDtoComprar dados, Long id) {
		
		var passagem = passagemRepository.getReferenceById(id);

		var aeronave = aeronaveRepository.getReferenceById(passagem.getIdAeronave().getId());

		var qtdAssento = passagem.getTipoAssento() == TipoAssento.ECONOMICO ? aeronave.getQtdAssentoEconomico()
				: aeronave.getQtdAssentoVip();

		if (qtdAssento < 1) {
			throw new ValidacaoException("Não há assentos disponvieis para essa categoria");
		} else {

			qtdAssento--;

			if (passagem.getTipoAssento() == TipoAssento.ECONOMICO) aeronave.setQtdAssentoEconomico(qtdAssento);
			else aeronave.setQtdAssentoVip(qtdAssento);

			aeronaveRepository.save(aeronave);
		}
	}

}
