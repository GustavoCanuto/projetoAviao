package br.com.magnasistemas.apipassagem.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.magnasistemas.apipassagem.entity.Cidade;
import br.com.magnasistemas.apipassagem.repository.CidadeRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;



@Component
public class BuscarCidade implements BuscarEntidade<Cidade> {

	@Autowired
	CidadeRepository cidadeRepository;
	
	Cidade cidade;
	
	public Cidade buscar(Long id) {

		if (id != null) {

			cidade = cidadeRepository.findById(id)
					.orElseThrow(() -> new ValidacaoException("Id da cidade informada não existe!"));
		}

		return cidade;
	}


	
	
	
	
}
