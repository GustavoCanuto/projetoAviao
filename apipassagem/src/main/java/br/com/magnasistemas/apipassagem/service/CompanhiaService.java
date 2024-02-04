package br.com.magnasistemas.apipassagem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;
import br.com.magnasistemas.apipassagem.repository.CompanhiaAereaRepository;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Service
public class CompanhiaService {

	@Autowired
	private CompanhiaAereaRepository companhiaAereaRepository;


	public CompanhiaAereaDtoDetalhar cadastrar(CompanhiaAereaDtoCadastro dados) {

		validaDuplicadas(dados);
		
		CompanhiaAerea aeropoporto = new CompanhiaAerea(dados); 

		 companhiaAereaRepository.save(aeropoporto);

		return new CompanhiaAereaDtoDetalhar(aeropoporto);

	}

	public Page<CompanhiaAereaDtoDetalhar> listar(Pageable paginacao) {

		return companhiaAereaRepository.findAll(paginacao).map(CompanhiaAereaDtoDetalhar::new);

	}



	public CompanhiaAereaDtoDetalhar detalhar(Long id) {

		return new CompanhiaAereaDtoDetalhar(companhiaAereaRepository.getReferenceById(id));

	}

	public CompanhiaAereaDtoDetalhar atualizarCadastro(CompanhiaAereaDtoAtualizar dados, long id) {

		validaDuplicadas(dados);
		
		CompanhiaAerea aeroporto = companhiaAereaRepository.getReferenceById(id);

		aeroporto.atualizarInformacoes(dados);

		companhiaAereaRepository.save(aeroporto);

		return new CompanhiaAereaDtoDetalhar(aeroporto);

	}

	public void deletaCadastro(Long id) {

		companhiaAereaRepository.deleteById(id);

	}
	
	private void validaDuplicadas(CompanhiaAereaDtoCadastro dados) {
		if (companhiaAereaRepository.existsByCnpj(dados.cnpj())) {
			throw new ValidacaoException("Cnpj já registrado!");
		}

		if (companhiaAereaRepository.existsByEmail(dados.email())) {
			throw new ValidacaoException("Email já registrado!");
		}
	}
	
	private void validaDuplicadas(CompanhiaAereaDtoAtualizar dados) {
		
		if (companhiaAereaRepository.existsByCnpj(dados.cnpj())) {
			throw new ValidacaoException("Cnpj já registrado!");
		}

		if (companhiaAereaRepository.existsByEmail(dados.email())) {
			throw new ValidacaoException("Email já registrado!");
		}
	}
}
