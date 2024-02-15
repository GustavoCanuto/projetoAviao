package br.com.magnasistemas.apipassagem.dto.endereco;

import br.com.magnasistemas.apipassagem.entity.Cidade;
import br.com.magnasistemas.apipassagem.entity.Endereco;

public class EnderecoDtoDetalhar{
		Long id;
		String logradouro; 
		String complemento; 
		String numero; 
		String cep; 
		Cidade cidade;
	
	public EnderecoDtoDetalhar(Endereco endereco) {
		this(endereco.getId(), endereco.getLogradouro(), endereco.getComplemento(),
				endereco.getNumero(),endereco.getCep(), endereco.getCidade());
	}

	public EnderecoDtoDetalhar(Long id, String logradouro, String complemento, String numero, String cep,
			Cidade cidade) {

		this.id = id;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.numero = numero;
		this.cep = cep;
		this.cidade = cidade;
	}

	public Long id() {
		return id;
	}
	
	public Long getId() {
		return id;
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

	public Cidade getCidade() {
		return cidade;
	}
	
}
