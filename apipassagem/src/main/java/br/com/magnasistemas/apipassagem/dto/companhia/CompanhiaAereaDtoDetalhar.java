package br.com.magnasistemas.apipassagem.dto.companhia;

import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;

public record CompanhiaAereaDtoDetalhar(
		
		Long id, 
		
		String nome,

		 String cnpj,

		 String email
		) {
	public CompanhiaAereaDtoDetalhar(CompanhiaAerea companhiaAerea) {
		this(companhiaAerea.getId(),companhiaAerea.getNome(), companhiaAerea.getCnpj(), companhiaAerea.getEmail());

	}
}
