package br.com.magnasistemas.apipassagem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.apipassagem.dto.Endereco.EnderecoDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.Endereco.EnderecoDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.Cidade;
import br.com.magnasistemas.apipassagem.entity.Endereco;
import br.com.magnasistemas.apipassagem.repository.EnderecoRepository;
import br.com.magnasistemas.apipassagem.service.buscador.BuscarCidade;



@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BuscarCidade getCidade;

	public EnderecoDtoDetalhar cadastrarEndereco(EnderecoDtoCadastro dados) {

		 Cidade cidade = getCidade.buscar(dados.idCidade());

		 Endereco endereco = new Endereco(dados, cidade); 

		 enderecoRepository.save(endereco);

		return new EnderecoDtoDetalhar(endereco);

	}

	public Page<EnderecoDtoDetalhar> listarEnderecos(Pageable paginacao) {

		return enderecoRepository.findAll(paginacao).map(EnderecoDtoDetalhar::new);

	}

	public Page<EnderecoDtoDetalhar> listarEnderecoPorCidade(Long id, Pageable paginacao) {

	
		return enderecoRepository.findByCidadeId(id, paginacao).map(EnderecoDtoDetalhar::new);

	}

	public EnderecoDtoDetalhar detalharBairro(Long id) {

		return new EnderecoDtoDetalhar(enderecoRepository.getReferenceById(id));

	}

	public EnderecoDtoDetalhar atualizarCadastro(EnderecoDtoCadastro dados, long id) {

		Cidade cidade = getCidade.buscar(dados.idCidade());

		Endereco endereco = enderecoRepository.getReferenceById(id);

		endereco.atualizarInformacoes(dados, cidade);

		enderecoRepository.save(endereco);

		return new EnderecoDtoDetalhar(endereco);

	}

	public void deletaCadastro(Long id) {

		enderecoRepository.deleteById(id);

	}

}
