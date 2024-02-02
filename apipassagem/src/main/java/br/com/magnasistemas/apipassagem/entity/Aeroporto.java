package br.com.magnasistemas.apipassagem.entity;

import br.com.magnasistemas.apipassagem.dto.aeroporto.AeroportoDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.aeroporto.AeroportoDtoCadastro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "tb_aeroporto")
@Entity(name = "Aeroporto")
public class Aeroporto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;

	@ManyToOne
	@JoinColumn(name = "fk_endereco")
	private Endereco endereco;

	public Aeroporto() {

	}

	public Aeroporto(Long id, String nome, String email, Endereco endereco) {

		this.id = id;
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
	}
	
	public Aeroporto(AeroportoDtoCadastro dados, Endereco endereco) {

		this.nome = dados.nome();
		this.email = dados.email();
		this.endereco = endereco;
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

	public void atualizarInformacoes(AeroportoDtoAtualizar dados, Endereco endereco) {

		if (dados.nome() != null) {
			this.nome = dados.nome();
		}

		if (dados.email() != null) {
			this.email = dados.email();
		}

		if (endereco != null) {
			this.endereco = endereco;
		}

	}
}
