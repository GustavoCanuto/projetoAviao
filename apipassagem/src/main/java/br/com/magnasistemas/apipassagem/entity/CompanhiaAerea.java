package br.com.magnasistemas.apipassagem.entity;

import br.com.magnasistemas.apipassagem.dto.companhiaAerea.CompanhiaAereaDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.companhiaAerea.CompanhiaAereaDtoCadastro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tb_companhia_aerea")
@Entity(name = "Companhia")
public class CompanhiaAerea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	String nome;

	 String cnpj;

	 String email;

	public CompanhiaAerea(Long id, String nome, String cnpj, String email) {
		super();
	
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
	}
	
	public CompanhiaAerea(CompanhiaAereaDtoCadastro dados) {


		this.nome = dados.nome();
		this.cnpj = dados.cnpj();
		this.email = dados.email();
	}

	public CompanhiaAerea() {
		super();
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
	
	public void atualizarInformacoes(CompanhiaAereaDtoAtualizar dados) {

		if (dados.nome() != null) {
			this.nome = dados.nome();
		}

		if (dados.email() != null) {
			this.email = dados.email();
		}

		if (dados.cnpj() != null) {
			this.cnpj = dados.cnpj();
		}

	}



}
