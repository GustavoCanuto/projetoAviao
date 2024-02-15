package br.com.magnasistemas.apipassagem.dto.aeroporto;

import br.com.magnasistemas.apipassagem.entity.Aeroporto;
import br.com.magnasistemas.apipassagem.entity.Endereco;

public class AeroportoDtoDetalhar{

		Long id;
		
		String nome;

		String email;

		Endereco endereco;

	public AeroportoDtoDetalhar(Aeroporto aeroporto) {
		this(aeroporto.getId(), aeroporto.getNome(), aeroporto.getEmail(), aeroporto.getEndereco());

	}

	public AeroportoDtoDetalhar(Long id, String nome, String email, Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
	}

	public Long id() {
		return id;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
}
