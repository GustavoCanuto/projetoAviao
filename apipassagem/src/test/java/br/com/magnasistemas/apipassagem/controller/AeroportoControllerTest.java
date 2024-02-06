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

import br.com.magnasistemas.apipassagem.dto.aeroporto.AeroportoDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.aeroporto.AeroportoDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.aeroporto.AeroportoDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.Aeroporto;
import br.com.magnasistemas.apipassagem.entity.Cidade;
import br.com.magnasistemas.apipassagem.entity.Endereco;
import br.com.magnasistemas.apipassagem.entity.Estado;
import br.com.magnasistemas.apipassagem.entity.Pais;
import br.com.magnasistemas.apipassagem.infra.PageResponse;
import br.com.magnasistemas.apipassagem.repository.AeroportoRepository;
import br.com.magnasistemas.apipassagem.repository.CidadeRepository;
import br.com.magnasistemas.apipassagem.repository.EnderecoRepository;
import br.com.magnasistemas.apipassagem.repository.EstadoRepository;
import br.com.magnasistemas.apipassagem.repository.PaisRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AeroportoControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private AeroportoRepository aeroportoRepository;
	
	@Autowired
	private PaisRepository paisRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
 
	@Autowired
	private EnderecoRepository enderecoRepository;

	private final String URI_PRINCIPAL = "/aeroporto";

	@BeforeEach
	void inicializar() {

		Endereco enderecoTeste = new Endereco("logradouro teste", "numero","complemento","12345678", criarCidade());
		Aeroporto aeroportoTeste = new Aeroporto( "aeroprto teste", "teste@gmail.com", enderecoTeste);
		
		enderecoRepository.save(enderecoTeste);
		aeroportoRepository.save(aeroportoTeste);
	}

	@AfterEach
	void finalizar() {

		aeroportoRepository.deleteAllAndResetSequence();
		enderecoRepository.deleteAllAndResetSequence();
		cidadeRepository.deleteAllAndResetSequence();
		estadoRepository.deleteAllAndResetSequence();
		paisRepository.deleteAllAndResetSequence();
	}

	@Test
	@DisplayName("Deveria listar ")
	void listarCenario1() {

		ResponseEntity<PageResponse<AeroportoDtoDetalhar>> responseEntity = restTemplate.exchange(URI_PRINCIPAL,
				HttpMethod.GET, null, new ParameterizedTypeReference<PageResponse<AeroportoDtoDetalhar>>() {
				});

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

		PageResponse<AeroportoDtoDetalhar> pageResponse = responseEntity.getBody();

		assertThat(pageResponse.isEmpty()).isFalse();
		assertThat(pageResponse.getContent()).isNotEmpty();
		assertThat(pageResponse.getContent().get(0).id()).isEqualTo(1L);
	}
	
	@Test
	@DisplayName("Deveria cadastrar um aeroporto com informações válidas")
	void cadastrarCenario1() {

		AeroportoDtoCadastro requestBody = new AeroportoDtoCadastro(1L, "aeroprto teste2", "teste2@gmail.com");

		ResponseEntity<AeroportoDtoDetalhar> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody,
				AeroportoDtoDetalhar.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isNotNull();

	}
	
	@Test
	@DisplayName("Nao Deveria cadastrar com badRequest")
	void erro400() {

		AeroportoDtoCadastro requestBody = new AeroportoDtoCadastro(1L, "aeroprto teste2", "teste2");

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody,
				String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		

	}
	
	@ParameterizedTest
	@MethodSource("parametrosCadastroInvalido")
	@DisplayName("Não Deveria cadastrar")
	void cadastrarInvalidoCenario1(Long endereco, String nome, String email, String mensagemDeErro) {

		AeroportoDtoCadastro requestBody = new AeroportoDtoCadastro(endereco, nome, email);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody()).contains(mensagemDeErro);

	}
	
	@ParameterizedTest
	@MethodSource("parametrosCadastroInvalido")
	@DisplayName("Não Deveria atualizar")
	void atualizarInvalidoCenario1(Long endereco, String nome, String email, String mensagemDeErro) {

		AeroportoDtoAtualizar requestBody = new AeroportoDtoAtualizar(endereco, nome, email);

		ResponseEntity<String> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/1", HttpMethod.PUT,
				new HttpEntity<>(requestBody), String.class, 1);
				

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody()).contains(mensagemDeErro);

	}

	static Stream<Arguments> parametrosCadastroInvalido() {
		return Stream.of(
				Arguments.of(1L, "aeroprto teste2", "teste@gmail.com", "Email já registrado!")
	

		);
	}
	
	@Test
	@DisplayName("Deveria manda exception ao usar endereco invalido")
	void cadastrarCenario2() {

		AeroportoDtoCadastro requestBody = new AeroportoDtoCadastro(15L, "aeroprto teste2", "teste2@gmail.com");

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isEqualTo("Id do Endereco não encontrado");

	}
	
	@Test
	@DisplayName("Deveria detalhar por ID")
	void detalharPorId() {
		Long idExistente = 1L;

		ResponseEntity<AeroportoDtoDetalhar> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}", HttpMethod.GET,
				null, AeroportoDtoDetalhar.class, idExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

	}

	@ParameterizedTest
	@MethodSource("parametrosAtualizar")
	@DisplayName("Deveria atualizar dados")
	void atualizarCenario1(Long id, String nome, String email) {
		
		Long idExistente = 1L;

		AeroportoDtoAtualizar requestBody = new AeroportoDtoAtualizar(id,nome,email);

		ResponseEntity<AeroportoDtoDetalhar> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}", HttpMethod.PUT,
				new HttpEntity<>(requestBody), AeroportoDtoDetalhar.class, idExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().id()).isEqualTo(idExistente);

	}
	
	static Stream<Arguments> parametrosAtualizar() {
		return Stream.of(Arguments.of(1L, "nome", "email@gmail.com"),
				Arguments.of(null,  "nome",null),
				Arguments.of(null,  "nome", "email@gmail.com")

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
	
	private Cidade criarCidade() {

		Pais paisTeste = new Pais("pais teste", "br");
		Estado estadoTeste = new Estado("estado teste", "uf", paisTeste);
		Cidade cidadeTeste = new Cidade("cidade teste", estadoTeste);

		paisRepository.save(paisTeste);
		estadoRepository.save(estadoTeste);
		cidadeRepository.save(cidadeTeste);

		return cidadeTeste;
	}

}
