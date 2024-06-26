package br.com.magnasistemas.apipassagem.entity;

import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoCadastro;
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

	private String nome;

	private String cnpj;

	private String email;

	public CompanhiaAerea(String nome, String cnpj, String email) {

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
