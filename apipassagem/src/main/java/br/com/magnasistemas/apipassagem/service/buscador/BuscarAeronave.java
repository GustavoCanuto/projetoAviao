package br.com.magnasistemas.apipassagem.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.entity.Aeronave;
import br.com.magnasistemas.apipassagem.repository.AeronaveRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class BuscarAeronave implements BuscarEntidade<Aeronave> {

	@Autowired
	AeronaveRepository aeronaveRepository;

	Aeronave aeronave;

	public Aeronave buscar(Long id) {

		if (id != null) {
		aeronave = aeronaveRepository.findById(id)
				.orElseThrow(() -> new ValidacaoException("Id não encontrado"));
		}
		return aeronave;
	}

	
	
}
