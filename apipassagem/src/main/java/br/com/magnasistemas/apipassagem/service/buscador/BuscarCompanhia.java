package br.com.magnasistemas.apipassagem.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;
import br.com.magnasistemas.apipassagem.repository.CompanhiaAereaRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class BuscarCompanhia implements BuscarEntidade<CompanhiaAerea> {

	@Autowired
	CompanhiaAereaRepository companhiaRepository;

	CompanhiaAerea companhia;

	public CompanhiaAerea buscar(Long id) {

		if (id != null) {
		companhia = companhiaRepository.findById(id)
				.orElseThrow(() -> new ValidacaoException("Id não encontrado"));
		}
		return companhia;
	}

}
