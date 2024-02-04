package br.com.magnasistemas.apipassagem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.Aeronave;
import br.com.magnasistemas.apipassagem.entity.Aeroporto;
import br.com.magnasistemas.apipassagem.entity.Passageiro;
import br.com.magnasistemas.apipassagem.entity.Passagem;
import br.com.magnasistemas.apipassagem.repository.PassagemRepository;
import br.com.magnasistemas.apipassagem.service.buscador.BuscarAeronave;
import br.com.magnasistemas.apipassagem.service.buscador.BuscarAeroporto;
import br.com.magnasistemas.apipassagem.service.buscador.BuscarPassageiro;
import br.com.magnasistemas.apipassagem.validacoes.passagem.ValidarCadastroPassagem;

@Service
public class PassagemService {

	@Autowired
	private PassagemRepository passagemRepository;

	@Autowired
	private BuscarAeronave getAeronave;

	@Autowired
	private BuscarAeroporto getAeroporto;

	@Autowired
	private BuscarPassageiro getPassageiro;
	
	@Autowired
	private List<ValidarCadastroPassagem> validadoresCadastro;

	public PassagemDtoDetalhar cadastrar(PassagemDtoCadastro dados) {

		Aeronave aeronave = getAeronave.buscar(dados.idAeronave());
		Aeroporto aeroportoOrigem = getAeroporto.buscar(dados.idOrigem());
		Aeroporto aeroportoDestino = getAeroporto.buscar(dados.idDestino());
		Passageiro passageiro = getPassageiro.buscar(dados.idPassageiro());

		validadoresCadastro.forEach(v -> v.validar(dados));
		
		Passagem passagem = new Passagem(dados, aeroportoOrigem, aeroportoDestino, aeronave, passageiro);

		passagemRepository.save(passagem);

		return new PassagemDtoDetalhar(passagem);

	}

	public Page<PassagemDtoDetalhar> listar(Pageable paginacao) {

		return passagemRepository.findAll(paginacao).map(PassagemDtoDetalhar::new);

	}

	public PassagemDtoDetalhar detalhar(Long id) {

		return new PassagemDtoDetalhar(passagemRepository.getReferenceById(id));

	}

	public void deletaCadastro(Long id) {

		passagemRepository.deleteById(id);

	}

}
