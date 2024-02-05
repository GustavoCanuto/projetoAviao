package br.com.magnasistemas.apipassagem.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;
import br.com.magnasistemas.apipassagem.infra.PageResponse;
import br.com.magnasistemas.apipassagem.repository.CompanhiaAereaRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CompanhiaControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;



	@Autowired
	private CompanhiaAereaRepository companhiaAereaRepository;

	private final String URI_PRINCIPAL = "/companhia";

	@BeforeEach
	void inicializar() {

		CompanhiaAerea companhiaTeste = new CompanhiaAerea("nome", "12345678902", "teste@gmail.com");

		companhiaAereaRepository.save(companhiaTeste);

	}

	@AfterEach
	void finalizar() {

		companhiaAereaRepository.deleteAllAndResetSequence();

	}

	@Test
	@DisplayName("Deveria listar ")
	void listarCenario1() {

		ResponseEntity<PageResponse<CompanhiaAereaDtoDetalhar>> responseEntity = restTemplate.exchange(URI_PRINCIPAL,
				HttpMethod.GET, null, new ParameterizedTypeReference<PageResponse<CompanhiaAereaDtoDetalhar>>() {
				});

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

		PageResponse<CompanhiaAereaDtoDetalhar> pageResponse = responseEntity.getBody();

		assertThat(pageResponse.isEmpty()).isFalse();
		assertThat(pageResponse.getContent()).isNotEmpty();
		assertThat(pageResponse.getContent().get(0).id()).isEqualTo(1L);
	}
	
	@Test
	@DisplayName("Deveria cadastrar uma companhia com informações válidas")
	void cadastrarCenario1() {

		CompanhiaAereaDtoCadastro requestBody = new CompanhiaAereaDtoCadastro("nome", "12345678901", "teste2@gmail.com");

		ResponseEntity<CompanhiaAereaDtoDetalhar> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody,
				CompanhiaAereaDtoDetalhar.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isNotNull();

	}


	@Test
	@DisplayName("Deveria detalhar por ID")
	void detalharPorId() {
		Long idExistente = 1L;

		ResponseEntity<CompanhiaAereaDtoDetalhar> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}",
				HttpMethod.GET, null, CompanhiaAereaDtoDetalhar.class, idExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

	}

	@ParameterizedTest
	@MethodSource("parametrosAtualizar")
	@DisplayName("Deveria atualizar dados")
	void atualizarCenario1(String var1, String var2,String var3) {

		Long idExistente = 1L;

		CompanhiaAereaDtoAtualizar requestBody = new CompanhiaAereaDtoAtualizar(var1, var2, var3);

		ResponseEntity<CompanhiaAereaDtoDetalhar> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}",
				HttpMethod.PUT, new HttpEntity<>(requestBody), CompanhiaAereaDtoDetalhar.class, idExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().id()).isEqualTo(idExistente);

	}

	static Stream<Arguments> parametrosAtualizar() {
		return Stream.of(Arguments.of("nome", "112345678901", "teste2@gmail.com"), 
				Arguments.of(null, "112345678901", "teste2@gmail.com"),
				Arguments.of("nome", null, null)

		);
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
