package br.com.magnasistemas.apipassagem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.apipassagem.dto.aeroporto.AeroportoDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.aeroporto.AeroportoDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.aeroporto.AeroportoDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.Aeroporto;
import br.com.magnasistemas.apipassagem.entity.Endereco;
import br.com.magnasistemas.apipassagem.repository.AeroportoRepository;
import br.com.magnasistemas.apipassagem.service.buscador.BuscarEndereco;

@Service
public class AeroportoService {

	@Autowired
	private AeroportoRepository aeroportoRepository;

	@Autowired
	private BuscarEndereco getEndereco;

	public AeroportoDtoDetalhar cadastrar(AeroportoDtoCadastro dados) {

		Endereco endereco = getEndereco.buscar(dados.idEndereco());

		 Aeroporto aeropoporto = new Aeroporto(dados, endereco); 

		 aeroportoRepository.save(aeropoporto);

		return new AeroportoDtoDetalhar(aeropoporto);

	}

	public Page<AeroportoDtoDetalhar> listar(Pageable paginacao) {

		return aeroportoRepository.findAll(paginacao).map(AeroportoDtoDetalhar::new);

	}



	public AeroportoDtoDetalhar detalhar(Long id) {

		return new AeroportoDtoDetalhar(aeroportoRepository.getReferenceById(id));

	}

	public AeroportoDtoDetalhar atualizarCadastro(AeroportoDtoAtualizar dados, long id) {

		Endereco endereco = getEndereco.buscar(dados.idEndereco());

		Aeroporto aeroporto = aeroportoRepository.getReferenceById(id);

		aeroporto.atualizarInformacoes(dados, endereco);

		aeroportoRepository.save(aeroporto);

		return new AeroportoDtoDetalhar(aeroporto);

	}

	public void deletaCadastro(Long id) {

		aeroportoRepository.deleteById(id);

	}
}
