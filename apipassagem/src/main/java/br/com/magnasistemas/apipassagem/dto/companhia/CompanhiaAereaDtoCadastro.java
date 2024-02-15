package br.com.magnasistemas.apipassagem.dto.companhia;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CompanhiaAereaDtoCadastro {
	@NotBlank
	@Size(max = 255)
	String nome;

	@NotNull
	@Pattern(regexp = "\\d{11}", message = "O CNPJ deve conter 11 numeros.")
	String cnpj;

	@NotBlank
	@Email(message = "O email deve ser válido.")
	String email;

	public CompanhiaAereaDtoCadastro(String nome, String cnpj, String email) {

		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
	}

	public String nome() {
		return nome;
	}

	public String cnpj() {
		return cnpj;
	}

	public String email() {
		return email;
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
