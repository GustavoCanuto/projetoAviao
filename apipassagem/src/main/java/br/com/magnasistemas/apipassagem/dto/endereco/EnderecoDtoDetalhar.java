package br.com.magnasistemas.apipassagem.dto.endereco;

import br.com.magnasistemas.apipassagem.entity.Cidade;
import br.com.magnasistemas.apipassagem.entity.Endereco;

public record EnderecoDtoDetalhar(Long id, String logradouro, String complemento, 
		String numero, String cep, Cidade cidade) {
	
	public EnderecoDtoDetalhar(Endereco endereco) {
		this(endereco.getId(), endereco.getLogradouro(), endereco.getComplemento(),
				endereco.getNumero(),endereco.getCep(), endereco.getCidade());
	}

}
