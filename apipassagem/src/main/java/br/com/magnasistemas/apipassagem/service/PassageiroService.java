package br.com.magnasistemas.apipassagem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.apipassagem.dto.passageiro.PassageiroDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.passageiro.PassageiroDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.Passageiro;
import br.com.magnasistemas.apipassagem.repository.PassageiroRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Service
public class PassageiroService {

	@Autowired
	private PassageiroRepository passageiroRepository;


	public PassageiroDtoDetalhar cadastrar(PassageiroDtoCadastro dados) {
		
		validaDuplicadas(dados);

		Passageiro aeropoporto = new Passageiro(dados); 

		 passageiroRepository.save(aeropoporto);

		return new PassageiroDtoDetalhar(aeropoporto);

	}

	public Page<PassageiroDtoDetalhar> listar(Pageable paginacao) {

		return passageiroRepository.findAll(paginacao).map(PassageiroDtoDetalhar::new);

	}

	public PassageiroDtoDetalhar detalhar(Long id) {

		return new PassageiroDtoDetalhar(passageiroRepository.getReferenceById(id));

	}

	public void deletaCadastro(Long id) {

		passageiroRepository.deleteById(id);

	}
	
	private void validaDuplicadas(PassageiroDtoCadastro dados) {
		if (passageiroRepository.existsByCpf(dados.cpf())) {
			throw new ValidacaoException("Cpf já registrado!");
		}

		if (passageiroRepository.existsByEmail(dados.email())) {
			throw new ValidacaoException("Email já registrado!");
		}
	}
}
