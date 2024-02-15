package br.com.magnasistemas.apipassagem.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EnderecoDtoCadastro{

		@NotNull Long idCidade;

		@Size(max = 255)
		@NotBlank String logradouro;

		@Size(max = 20)
		String complemento;
		
		@Size(max = 8)
		@NotNull String numero;

		@NotNull @Pattern(regexp = "\\d{8}") String cep;
		
		
		public Long idCidade() {
			return idCidade;
		}

		public String logradouro() {
			return logradouro;
		}

		public String complemento() {
			return complemento;
		}

		public String numero() {
			return numero;
		}

		public String cep() {
			return cep;
		}

		public EnderecoDtoCadastro(Long idCidade, String logradouro,
				String complemento, String numero,
				String cep) {

			this.idCidade = idCidade;
			this.logradouro = logradouro;
			this.complemento = complemento;
			this.numero = numero;
			this.cep = cep;
		}

		public Long getIdCidade() {
			return idCidade;
		}

		public String getLogradouro() {
			return logradouro;
		}

		public String getComplemento() {
			return complemento;
		}

		public String getNumero() {
			return numero;
		}

		public String getCep() {
			return cep;
		}

		

}
