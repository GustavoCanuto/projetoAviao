package br.com.magnasistemas.apipassagem.dto.aeroporto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AeroportoDtoCadastro {

	@NotNull
	Long idEndereco;

	@NotBlank
	@Size(max = 255)
	String nome;

	@NotBlank
	@Email(message = "O email deve ser válido.")
	String email;

	public AeroportoDtoCadastro(Long idEndereco,  String nome,
			 String email) {
		
		this.idEndereco = idEndereco;
		this.nome = nome;
		this.email = email;
	}

	public Long idEndereco() {
		return idEndereco;
	}

	public String nome() {
		return nome;
	}

	public String email() {
		return email;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	
	
	
	

}