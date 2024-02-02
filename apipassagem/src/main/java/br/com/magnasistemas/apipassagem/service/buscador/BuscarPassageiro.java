package br.com.magnasistemas.apipassagem.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.entity.Passageiro;
import br.com.magnasistemas.apipassagem.repository.PassageiroRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class BuscarPassageiro implements BuscarEntidade<Passageiro> {

	@Autowired
	PassageiroRepository passageiroRepository;

	Passageiro passageiro;

	public Passageiro buscar(Long id) {

		if (id != null) {
		passageiro = passageiroRepository.findById(id)
				.orElseThrow(() -> new ValidacaoException("Id não encontrado"));
		}
		return passageiro;
	}

}
