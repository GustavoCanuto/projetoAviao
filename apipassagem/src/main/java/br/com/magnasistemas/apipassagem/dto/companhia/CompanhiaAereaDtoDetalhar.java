package br.com.magnasistemas.apipassagem.dto.companhia;

import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;

public class CompanhiaAereaDtoDetalhar {

	Long id;

	String nome;

	String cnpj;

	String email;

	public CompanhiaAereaDtoDetalhar(CompanhiaAerea companhiaAerea) {
		this(companhiaAerea.getId(), companhiaAerea.getNome(), companhiaAerea.getCnpj(), companhiaAerea.getEmail());

	}

	public CompanhiaAereaDtoDetalhar(Long id, String nome, String cnpj, String email) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
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

	public String getCnpj() {
		return cnpj;
	}

	public String getEmail() {
		return email;
	}

}
