package br.com.magnasistemas.apipassagem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.Aeronave;
import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;
import br.com.magnasistemas.apipassagem.repository.AeronaveRepository;
import br.com.magnasistemas.apipassagem.service.buscador.BuscarCompanhia;
import br.com.magnasistemas.apipassagem.validacoes.ValidacaoException;

@Service
public class AeronaveService {

	@Autowired
	private AeronaveRepository aeronaveRepository;

	@Autowired
	private BuscarCompanhia getCompanhia;

	public AeronaveDtoDetalhar cadastrar(AeronaveDtoCadastro dados) {

		validaDuplicadas(dados);

		CompanhiaAerea companhia = getCompanhia.buscar(dados.idCompanhia());

		Aeronave entidade = new Aeronave(dados, companhia);

		aeronaveRepository.save(entidade);

		return new AeronaveDtoDetalhar(entidade);

	}

	public Page<AeronaveDtoDetalhar> listar(Pageable paginacao) {

		return aeronaveRepository.findAll(paginacao).map(AeronaveDtoDetalhar::new);

	}

	public AeronaveDtoDetalhar detalhar(Long id) {

		return new AeronaveDtoDetalhar(aeronaveRepository.getReferenceById(id));

	}

	public AeronaveDtoDetalhar atualizarCadastro(AeronaveDtoAtualizar dados, long id) {

		CompanhiaAerea companhia = getCompanhia.buscar(dados.idCompanhia());

		Aeronave entidade = aeronaveRepository.getReferenceById(id);

		entidade.atualizarInformacoes(dados, companhia);

		aeronaveRepository.save(entidade);

		return new AeronaveDtoDetalhar(entidade);

	}

	public void deletaCadastro(Long id) {

		aeronaveRepository.deleteById(id);

	}

	private void validaDuplicadas(AeronaveDtoCadastro dados) {

		if (aeronaveRepository.existsByNsa(dados.nsa())) {
			throw new ValidacaoException("Nsa já registrado!");
		}
	}

}
