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

import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoDetalhar;
import br.com.magnasistemas.apipassagem.dto.aeroporto.AeroportoDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.Aeronave;
import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;
import br.com.magnasistemas.apipassagem.infra.PageResponse;
import br.com.magnasistemas.apipassagem.repository.AeronaveRepository;
import br.com.magnasistemas.apipassagem.repository.CompanhiaAereaRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AeronaveControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private AeronaveRepository aeronaveRepository;

	@Autowired
	private CompanhiaAereaRepository companhiaAereaRepository;

	private final String URI_PRINCIPAL = "/aeronave";

	@BeforeEach
	void inicializar() {

		CompanhiaAerea companhiaTeste = new CompanhiaAerea("nome", "04444040", "teste@gmail.com");
		Aeronave aeronaveTeste = new Aeronave(companhiaTeste, 15L, 20l, "nsa", "aviao");

		companhiaAereaRepository.save(companhiaTeste);
		aeronaveRepository.save(aeronaveTeste);
	}

	@AfterEach
	void finalizar() {

		aeronaveRepository.deleteAllAndResetSequence();
		companhiaAereaRepository.deleteAllAndResetSequence();

	}

	@Test
	@DisplayName("Deveria listar ")
	void listarCenario1() {

		ResponseEntity<PageResponse<AeronaveDtoDetalhar>> responseEntity = restTemplate.exchange(URI_PRINCIPAL,
				HttpMethod.GET, null, new ParameterizedTypeReference<PageResponse<AeronaveDtoDetalhar>>() {
				});

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

		PageResponse<AeronaveDtoDetalhar> pageResponse = responseEntity.getBody();

		assertThat(pageResponse.isEmpty()).isFalse();
		assertThat(pageResponse.getContent()).isNotEmpty();
		assertThat(pageResponse.getContent().get(0).id()).isEqualTo(1L);
	}

	
	@Test
	@DisplayName("Deveria cadastrar uma aeronave com informações válidas")
	void cadastrarCenario1() {

		AeronaveDtoCadastro requestBody = new AeronaveDtoCadastro(1L, 20L, 25L, "XXX", "MODELO");

		ResponseEntity<AeronaveDtoDetalhar> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody,
				AeronaveDtoDetalhar.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isNotNull();

	}
	
	@ParameterizedTest
	@MethodSource("parametrosCadastroInvalido")
	@DisplayName("Não Deveria cadastrar aeronave")
	void cadastrarInvalidoCenario1(Long companhia, Long qtdAssentoEconomico, Long qtdAssentoVip, String nsa, String modelo, String mensagemDeErro) {

		AeronaveDtoCadastro requestBody = new AeronaveDtoCadastro(companhia, qtdAssentoEconomico,  qtdAssentoVip, nsa,  modelo);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody()).contains(mensagemDeErro);

	}

	static Stream<Arguments> parametrosCadastroInvalido() {
		return Stream.of(
				Arguments.of(1L, 20L, 25L, "nsa", "MODELO",
						"Nsa já registrado!")

		);
	}

	@Test
	@DisplayName("Deveria manda exception ao usar companhia invalido")
	void cadastrarCenario2() {

		AeronaveDtoCadastro requestBody = new AeronaveDtoCadastro(15L, 20L, 25l, "XXX", "MODELO");

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isEqualTo("Id não encontrado");

	}

	@Test
	@DisplayName("Deveria detalhar por ID")
	void detalharPorId() {
		Long idExistente = 1L;

		ResponseEntity<AeronaveDtoDetalhar> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}",
				HttpMethod.GET, null, AeronaveDtoDetalhar.class, idExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

	}

	@ParameterizedTest
	@MethodSource("parametrosAtualizar")
	@DisplayName("Deveria atualizar dados")
	void atualizarCenario1(Long id, Long economico,Long vip) {

		Long idExistente = 1L;

		AeronaveDtoAtualizar requestBody = new AeronaveDtoAtualizar(id, economico, vip);

		ResponseEntity<AeroportoDtoDetalhar> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}",
				HttpMethod.PUT, new HttpEntity<>(requestBody), AeroportoDtoDetalhar.class, idExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().id()).isEqualTo(idExistente);

	}

	static Stream<Arguments> parametrosAtualizar() {
		return Stream.of(Arguments.of(1L, 20L, 25l), 
				Arguments.of(null, 20L, 25l),
				Arguments.of(1L, null, null)

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
