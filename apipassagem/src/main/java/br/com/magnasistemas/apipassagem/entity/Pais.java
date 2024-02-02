package br.com.magnasistemas.apipassagem.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "tb_pais")
@Entity(name = "Pais")
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;

	@OneToMany(mappedBy = "pais")
	@JsonIgnore
	private List<Estado> estado = new ArrayList<>();

	public Pais() {

	}

	public Pais(String nome, String sigla) {

		this.nome = nome;
		this.sigla = sigla;

	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

}
