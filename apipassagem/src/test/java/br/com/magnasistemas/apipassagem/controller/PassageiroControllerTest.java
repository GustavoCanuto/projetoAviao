package br.com.magnasistemas.apipassagem.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

import br.com.magnasistemas.apipassagem.dto.passageiro.PassageiroDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.passageiro.PassageiroDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.Passageiro;
import br.com.magnasistemas.apipassagem.infra.PageResponse;
import br.com.magnasistemas.apipassagem.repository.PassageiroRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PassageiroControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PassageiroRepository passageiroRepository;

	private final String URI_PRINCIPAL = "/passageiro";

	@BeforeEach
	void inicializar() {

		Passageiro passageiroTeste = new Passageiro("nome", "44844444444", LocalDate.of(2023, 10, 2), "teste@gmail.com");

		passageiroRepository.save(passageiroTeste);

	}

	@AfterEach
	void finalizar() {

		passageiroRepository.deleteAllAndResetSequence();

	}

	@Test
	@DisplayName("Deveria listar ")
	void listarCenario1() {

		ResponseEntity<PageResponse<PassageiroDtoDetalhar>> responseEntity = restTemplate.exchange(URI_PRINCIPAL,
				HttpMethod.GET, null, new ParameterizedTypeReference<PageResponse<PassageiroDtoDetalhar>>() {
				});

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

		PageResponse<PassageiroDtoDetalhar> pageResponse = responseEntity.getBody();

		assertThat(pageResponse.isEmpty()).isFalse();
		assertThat(pageResponse.getContent()).isNotEmpty();
		assertThat(pageResponse.getContent().get(0).id()).isEqualTo(1L);
	}
	
	@Test
	@DisplayName("Deveria cadastrar um passageiro com informações válidas")
	void cadastrarCenario1() {

		PassageiroDtoCadastro requestBody = new PassageiroDtoCadastro("nome", "44844444447", LocalDate.of(2023, 10, 2), "teste2@gmail.com");

		ResponseEntity<PassageiroDtoDetalhar> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody,
				PassageiroDtoDetalhar.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isNotNull();

	}


	@Test
	@DisplayName("Deveria detalhar por ID")
	void detalharPorId() {
		Long idExistente = 1L;

		ResponseEntity<PassageiroDtoDetalhar> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}",
				HttpMethod.GET, null, PassageiroDtoDetalhar.class, idExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

	}


	@Test
	@DisplayName("Deveria excluir  por ID")
	void excluirPorId() {
		Long idExistente = 1L;

		ResponseEntity<Void> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}", HttpMethod.DELETE, null,
				Void.class, idExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
