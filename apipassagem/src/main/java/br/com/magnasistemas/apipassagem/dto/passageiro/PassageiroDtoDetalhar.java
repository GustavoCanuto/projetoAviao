package br.com.magnasistemas.apipassagem.dto.passageiro;

import java.time.LocalDate;

import br.com.magnasistemas.apipassagem.entity.Passageiro;

public class PassageiroDtoDetalhar {

	Long id;

	String nomeCompleto;

	String cpf;

	LocalDate dataNascimento;

	String email;

	public PassageiroDtoDetalhar(Passageiro passageiro) {
		this(passageiro.getId(), passageiro.getNomeCompleto(), passageiro.getCpf(), passageiro.getDataNascimento(),
				passageiro.getEmail());

	}

	public PassageiroDtoDetalhar(Long id, String nomeCompleto, String cpf, LocalDate dataNascimento, String email) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}

	public Long id() {
		return id;
	}

	public Long getId() {
		return id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getEmail() {
		return email;
	}

}