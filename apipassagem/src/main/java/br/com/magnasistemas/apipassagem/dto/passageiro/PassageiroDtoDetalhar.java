package br.com.magnasistemas.apipassagem.dto.passageiro;

import java.time.LocalDate;

import br.com.magnasistemas.apipassagem.entity.Passageiro;

public record PassageiroDtoDetalhar(
		
		Long id,

		String nomeCompleto,

		String cpf,

		LocalDate dataNascimento,

		String email) {
	
	public PassageiroDtoDetalhar(Passageiro passageiro) {
		this(passageiro.getId(), passageiro.getNomeCompleto(), passageiro.getCpf(),passageiro.getDataNascimento(), passageiro.getEmail());

	}


}