package br.com.magnasistemas.apipassagem.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.entity.Aeroporto;
import br.com.magnasistemas.apipassagem.repository.AeroportoRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class BuscarAeroporto implements BuscarEntidade<Aeroporto> {

	@Autowired
	AeroportoRepository aeroportoRepository;

	Aeroporto aeroporto;

	public Aeroporto buscar(Long id) {

		if (id != null) {
		aeroporto = aeroportoRepository.findById(id)
				.orElseThrow(() -> new ValidacaoException("Id não encontrado"));
		}
		return aeroporto;
	}

}
