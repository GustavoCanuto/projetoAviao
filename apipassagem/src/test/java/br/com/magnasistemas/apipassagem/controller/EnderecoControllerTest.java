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

import br.com.magnasistemas.apipassagem.dto.endereco.EnderecoDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.endereco.EnderecoDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.Cidade;
import br.com.magnasistemas.apipassagem.entity.Endereco;
import br.com.magnasistemas.apipassagem.entity.Estado;
import br.com.magnasistemas.apipassagem.entity.Pais;
import br.com.magnasistemas.apipassagem.infra.PageResponse;
import br.com.magnasistemas.apipassagem.repository.CidadeRepository;
import br.com.magnasistemas.apipassagem.repository.EnderecoRepository;
import br.com.magnasistemas.apipassagem.repository.EstadoRepository;
import br.com.magnasistemas.apipassagem.repository.PaisRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EnderecoControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PaisRepository paisRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
 
	@Autowired
	private EnderecoRepository enderecoRepository;

	private final String URI_PRINCIPAL = "/endereco";
	private final String URI_PESQUISA_CIDADE = "/endereco/cidade";

	@BeforeEach
	void inicializar() {

		Endereco enderecoTeste = new Endereco("logradouro teste", "numero","complemento","12345678", criarCidade());

		enderecoRepository.save(enderecoTeste);

	}

	@AfterEach
	void finalizar() {


		enderecoRepository.deleteAllAndResetSequence();
		cidadeRepository.deleteAllAndResetSequence();
		estadoRepository.deleteAllAndResetSequence();
		paisRepository.deleteAllAndResetSequence();
	}

	@Test
	@DisplayName("Deveria cadastrar um endereco com informações válidas")
	void cadastrarBairroCenario1() {

		EnderecoDtoCadastro requestBody = new EnderecoDtoCadastro(1L, "logradouro teste", "complemento","numero","12345678");

		ResponseEntity<EnderecoDtoDetalhar> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody,
				EnderecoDtoDetalhar.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().complemento()).isEqualTo("complemento");

	}

	@Test
	@DisplayName("Deveria manda exception ao usar cidade invalida")
	void cadastrarBairroCenario2() {

		EnderecoDtoCadastro requestBody = new EnderecoDtoCadastro(15L, "logradouro teste", "complemento","numero","12345678");

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isEqualTo("Id da cidade informada não existe!");

	}

	@Test
	@DisplayName("Deveria detalhar um endereco por ID")
	void detalharBairroPorId() {
		Long idDoBairroExistente = 1L;

		ResponseEntity<EnderecoDtoDetalhar> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}", HttpMethod.GET,
				null, EnderecoDtoDetalhar.class, idDoBairroExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().complemento()).isEqualTo("complemento");

	}

	@ParameterizedTest
	@MethodSource("parametrosAtualizar")
	@DisplayName("Deveria atualizar dados")
	void atualizarBairroPorIdCenario1(Long id, String logradouro, String complemento,String numero,String cep) {
		Long idDoBairroExistente = 1L;

		EnderecoDtoCadastro requestBody = new EnderecoDtoCadastro(id,logradouro, complemento,numero,cep);

		ResponseEntity<EnderecoDtoDetalhar> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}", HttpMethod.PUT,
				new HttpEntity<>(requestBody), EnderecoDtoDetalhar.class, idDoBairroExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody().id()).isEqualTo(idDoBairroExistente);

	}

	@Test
	@DisplayName("Deveria mandar erro ao tentar atualizar com cidade invalida")
	void atualizarBairroPorIdCenario2() {
		Long idDoBairroExistente = 1L;

		EnderecoDtoCadastro requestBody = new EnderecoDtoCadastro(15L, "", "complemento","numero","12345678");

		ResponseEntity<String> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}", HttpMethod.PUT,
				new HttpEntity<>(requestBody), String.class, idDoBairroExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isEqualTo("Id da cidade informada não existe!");

	}

	@Test
	@DisplayName("Deveria listar ")
	void listarCenario1() {

		ResponseEntity<PageResponse<EnderecoDtoDetalhar>> responseEntity = restTemplate.exchange(URI_PRINCIPAL,
				HttpMethod.GET, null, new ParameterizedTypeReference<PageResponse<EnderecoDtoDetalhar>>() {
				});

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

		PageResponse<EnderecoDtoDetalhar> pageResponse = responseEntity.getBody();

		assertThat(pageResponse.isEmpty()).isFalse();
		assertThat(pageResponse.getContent()).isNotEmpty();
		assertThat(pageResponse.getContent().get(0).id()).isEqualTo(1L);
	}


	@Test
	@DisplayName("Deveria listar endereco pelo id da cidade")
	void listarBairrosPorCidadeCenario1() {
		Long idCidadeExistente = 1L;

		ResponseEntity<PageResponse<EnderecoDtoDetalhar>> responseEntity = restTemplate.exchange(
				URI_PESQUISA_CIDADE + "/{id}", HttpMethod.GET, null,
				new ParameterizedTypeReference<PageResponse<EnderecoDtoDetalhar>>() {
				}, idCidadeExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

		PageResponse<EnderecoDtoDetalhar> pageResponse = responseEntity.getBody();

		assertThat(pageResponse.isEmpty()).isFalse();
		assertThat(pageResponse.getContent()).isNotEmpty();
		assertThat(pageResponse.getContent().get(0).id()).isEqualTo(1L);
	}


	@Test
	@DisplayName("Deveria excluir um endereco por ID")
	void excluirBairroPorId() {
		Long idDoBairroExistente = 1L;

		ResponseEntity<Void> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}", HttpMethod.DELETE, null,
				Void.class, idDoBairroExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	static Stream<Arguments> parametrosAtualizar() {
		return Stream.of(Arguments.of(1L, "logradouro teste", "complemento","numero","12345678"),
				Arguments.of(null, "logradouro teste", "complemento","numero","12345678", null),
				Arguments.of(null, null, "complemento","numero","12345678"),
				Arguments.of(null, null, null,null,"12345678"),
				Arguments.of(null, "logradouro teste", "complemento","numero","12345678")

		);
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
