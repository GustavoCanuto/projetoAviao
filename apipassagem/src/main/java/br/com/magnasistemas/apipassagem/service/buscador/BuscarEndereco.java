package br.com.magnasistemas.apipassagem.service.buscador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import br.com.magnasistemas.apipassagem.entity.Endereco;
import br.com.magnasistemas.apipassagem.repository.EnderecoRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Component
public class BuscarEndereco implements BuscarEntidade<Endereco> {

	@Autowired
	EnderecoRepository enderecoRepository;

	Endereco endereco;

	public Endereco buscar(Long id) {

		if (id != null) {
		endereco = enderecoRepository.findById(id)
				.orElseThrow(() -> new ValidacaoException("Id do Endereco não encontrado"));
		}
		return endereco;
	}

}
