package br.com.magnasistemas.apipassagem.controller;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.magnasistemas.apipassagem.dto.estado.EstadoDtoListagem;
import br.com.magnasistemas.apipassagem.entity.Estado;
import br.com.magnasistemas.apipassagem.entity.Pais;
import br.com.magnasistemas.apipassagem.repository.EstadoRepository;
import br.com.magnasistemas.apipassagem.repository.PaisRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class EstadoControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PaisRepository paisRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Test
	@DisplayName("Deveria listar todos os estados")
	void listarEstadosCenario1() {
		
		Pais paisTeste = new Pais("pais teste", "br");
		Estado estadoTeste = new Estado("estado teste ", "uf",paisTeste);

		paisRepository.save(paisTeste);
		estadoRepository.save(estadoTeste);

		ResponseEntity<List<EstadoDtoListagem>> responseEntity = restTemplate.exchange("/estado",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<EstadoDtoListagem>>() {
				});

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().get(0).id()).isEqualTo(estadoTeste.getId());
		
		estadoRepository.deleteAllAndResetSequence();  
		paisRepository.deleteAllAndResetSequence();

	}

}