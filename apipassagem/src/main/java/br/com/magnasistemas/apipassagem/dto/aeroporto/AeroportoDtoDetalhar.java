package br.com.magnasistemas.apipassagem.dto.aeroporto;

import br.com.magnasistemas.apipassagem.entity.Aeroporto;
import br.com.magnasistemas.apipassagem.entity.Endereco;

public record AeroportoDtoDetalhar(

		Long id, String nome,

		String email,

		Endereco endereco

)

{

	public AeroportoDtoDetalhar(Aeroporto aeroporto) {
		this(aeroporto.getId(), aeroporto.getNome(), aeroporto.getEmail(), aeroporto.getEndereco());

	}
}
