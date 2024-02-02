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

@Service
public class AeronaveService {

	@Autowired
	private AeronaveRepository aeronaveRepository;

	@Autowired
	private BuscarCompanhia getCompanhia;

	public  AeronaveDtoDetalhar cadastrar( AeronaveDtoCadastro dados) {

		CompanhiaAerea endereco = getCompanhia.buscar(dados.idCompanhia());

		 Aeronave aeropoporto = new Aeronave(dados, endereco); 

		 aeronaveRepository.save(aeropoporto);

		return new  AeronaveDtoDetalhar(aeropoporto);

	}

	public Page<AeronaveDtoDetalhar> listar(Pageable paginacao) {

		return aeronaveRepository.findAll(paginacao).map(AeronaveDtoDetalhar::new);

	}



	public AeronaveDtoDetalhar detalhar(Long id) {

		return new AeronaveDtoDetalhar(aeronaveRepository.getReferenceById(id));

	}

	public AeronaveDtoDetalhar atualizarCadastro(AeronaveDtoAtualizar dados, long id) {

		CompanhiaAerea endereco = getCompanhia.buscar(dados.idCompanhia());

		Aeronave aeroporto = aeronaveRepository.getReferenceById(id);

		aeroporto.atualizarInformacoes(dados, endereco);

		aeronaveRepository.save(aeroporto);

		return new AeronaveDtoDetalhar(aeroporto);

	}

	public void deletaCadastro(Long id) {

		aeronaveRepository.deleteById(id);

	}
}
