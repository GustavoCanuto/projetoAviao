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


import br.com.magnasistemas.apipassagem.dto.pais.PaisDtoListagem;
import br.com.magnasistemas.apipassagem.entity.Pais;
import br.com.magnasistemas.apipassagem.repository.PaisRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PaisControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PaisRepository paisRepository;

	@Test
	@DisplayName("Deveria listar todos os estados")
	void listarCenario1() {
		
		Pais paisTeste = new Pais("pais teste ", "br");

		paisRepository.save(paisTeste);

		ResponseEntity<List<PaisDtoListagem>> responseEntity = restTemplate.exchange("/pais",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<PaisDtoListagem>>() {
				});

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().get(0).id()).isEqualTo(paisTeste.getId());
		
		paisRepository.deleteAllAndResetSequence();  

	}
}
