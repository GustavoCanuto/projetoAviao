package br.com.magnasistemas.apipassagem.entity;

import br.com.magnasistemas.apipassagem.dto.Endereco.EnderecoDtoCadastro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Table(name = "tb_endereco")
@Entity(name = "Endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_cidade")
	private Cidade cidade;

	private String logradouro;
	private String numero;
	private String complemento;
	private String cep;

	public Endereco() {

	}

	public Endereco(EnderecoDtoCadastro dados, Cidade cidade) {
		this.cidade = cidade;
		this.logradouro = dados.logradouro();
		this.cep = dados.cep();
		this.numero = dados.numero();
		this.complemento = dados.complemento();
	}
	
	public Endereco(String logradouro,String numero,String complemento,String cep, Cidade cidade) {
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}

	public Long getId() {
		return id;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getCep() {
		return cep;
	}

	public void atualizarInformacoes(EnderecoDtoCadastro dados, Cidade cidade) {
		
		if (dados.logradouro() != null && !dados.logradouro().isBlank()) {
			this.logradouro = dados.logradouro();
		}

		if (dados.complemento() != null) {
			this.complemento = dados.complemento();
		}
		
		if (dados.numero() != null) {
			this.numero = dados.numero();
		}

		if (dados.cep() != null) {
			this.cep = dados.cep();
		}

		if (cidade != null) {
			this.cidade = cidade;
		}

		
	}
	


}
