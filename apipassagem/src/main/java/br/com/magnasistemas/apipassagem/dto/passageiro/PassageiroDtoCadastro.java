package br.com.magnasistemas.apipassagem.dto.passageiro;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PassageiroDtoCadastro {
	@NotBlank
	@Size(max = 255)
	String nomeCompleto;

	@NotNull
	@Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 numeros.")
	String cpf;

	@Past(message = "A data de nascimento deve ser no passado.")
	LocalDate dataNascimento;

	@NotNull
	@Email(message = "O email deve ser válido.")
	String email;

	public PassageiroDtoCadastro(String nomeCompleto, String cpf, LocalDate dataNascimento, String email) {

		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}

	public String nomeCompleto() {
		return nomeCompleto;
	}

	public String cpf() {
		return cpf;
	}

	public LocalDate dataNascimento() {
		return dataNascimento;
	}

	public String email() {
		return email;
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
