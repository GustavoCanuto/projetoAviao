package br.com.magnasistemas.apipassagem.entity;

import java.time.LocalDate;

import br.com.magnasistemas.apipassagem.dto.passageiro.PassageiroDtoCadastro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tb_passageiro")
@Entity(name = "Passageiro")
public class Passageiro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	String nomeCompleto;

	String cpf;

	LocalDate dataNascimento;

	public Passageiro() {
		super();
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

	String email;

	public Passageiro(String nomeCompleto, String cpf, LocalDate dataNascimento, String email) {
		
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}
	
	public Passageiro(PassageiroDtoCadastro dados) {
	

		this.nomeCompleto = dados.nomeCompleto();
		this.cpf = dados.cpf();
		this.dataNascimento = dados.dataNascimento();
		this.email = dados.email();
	}


}
