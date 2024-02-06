package br.com.magnasistemas.apipassagem.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoDetalhar;
import br.com.magnasistemas.apipassagem.entity.Aeronave;
import br.com.magnasistemas.apipassagem.entity.Aeroporto;
import br.com.magnasistemas.apipassagem.entity.Cidade;
import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;
import br.com.magnasistemas.apipassagem.entity.Endereco;
import br.com.magnasistemas.apipassagem.entity.Estado;
import br.com.magnasistemas.apipassagem.entity.Pais;
import br.com.magnasistemas.apipassagem.entity.Passageiro;
import br.com.magnasistemas.apipassagem.entity.Passagem;
import br.com.magnasistemas.apipassagem.enums.TipoAssento;
import br.com.magnasistemas.apipassagem.infra.PageResponse;
import br.com.magnasistemas.apipassagem.repository.AeronaveRepository;
import br.com.magnasistemas.apipassagem.repository.AeroportoRepository;
import br.com.magnasistemas.apipassagem.repository.CidadeRepository;
import br.com.magnasistemas.apipassagem.repository.CompanhiaAereaRepository;
import br.com.magnasistemas.apipassagem.repository.EnderecoRepository;
import br.com.magnasistemas.apipassagem.repository.EstadoRepository;
import br.com.magnasistemas.apipassagem.repository.PaisRepository;
import br.com.magnasistemas.apipassagem.repository.PassageiroRepository;
import br.com.magnasistemas.apipassagem.repository.PassagemRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PassagemControllerTest {

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

	@Autowired
	private PassageiroRepository passageiroRepository;

	@Autowired
	private AeroportoRepository aeroportoRepository;

	@Autowired
	private AeronaveRepository aeronaveRepository;

	@Autowired
	private CompanhiaAereaRepository companhiaAereaRepository;

	@Autowired
	private PassagemRepository passagemRepository;

	private final String URI_PRINCIPAL = "/passagem";

	@BeforeEach
	void inicializar() {

		Endereco enderecoTeste = new Endereco("logradouro teste", "numero", "complemento", "12345678", criarCidade());
		Aeroporto aeroportoTesteOrigem = new Aeroporto("aeroprto teste", "teste@gmail.com", enderecoTeste);
		Aeroporto aeroportoTesteDestino = new Aeroporto("aeroprto teste2", "teste2@gmail.com", enderecoTeste);

		enderecoRepository.save(enderecoTeste);
		aeroportoRepository.save(aeroportoTesteOrigem);
		aeroportoRepository.save(aeroportoTesteDestino);

		CompanhiaAerea companhiaTeste = new CompanhiaAerea("nome", "04444040", "teste@gmail.com");
		Aeronave aeronaveTeste = new Aeronave(companhiaTeste, 15L, 20L, "nsa", "aviao");
		Aeronave aeronaveTesteSemAssento = new Aeronave(companhiaTeste, 0L, 0L, "nsa", "aviao");

		companhiaAereaRepository.save(companhiaTeste);
		aeronaveRepository.save(aeronaveTeste);
		aeronaveRepository.save(aeronaveTesteSemAssento);

		Passageiro passageiroTeste = new Passageiro("nome", "44844444444", LocalDate.of(2023, 10, 2),
				"teste@gmail.com");

		passageiroRepository.save(passageiroTeste);

		Passagem passagemTeste = new Passagem(LocalDateTime.of(2023, 10, 1, 15, 30),
				LocalDateTime.of(2023, 10, 2, 15, 30), LocalDateTime.of(2023, 10, 3, 15, 30), aeroportoTesteOrigem,
				aeroportoTesteDestino, aeronaveTeste, passageiroTeste, 200D, TipoAssento.ECONOMICO);

		passagemRepository.save(passagemTeste);
	}

	@AfterEach
	void finalizar() {

		passagemRepository.deleteAllAndResetSequence();
		passageiroRepository.deleteAllAndResetSequence();
		aeronaveRepository.deleteAllAndResetSequence();
		companhiaAereaRepository.deleteAllAndResetSequence();
		passageiroRepository.deleteAllAndResetSequence();
		aeroportoRepository.deleteAllAndResetSequence();
		enderecoRepository.deleteAllAndResetSequence();
		cidadeRepository.deleteAllAndResetSequence();
		estadoRepository.deleteAllAndResetSequence();
		paisRepository.deleteAllAndResetSequence();

	}

	@Test
	@DisplayName("Deveria listar ")
	void listarCenario1() {

		ResponseEntity<PageResponse<PassagemDtoDetalhar>> responseEntity = restTemplate.exchange(URI_PRINCIPAL,
				HttpMethod.GET, null, new ParameterizedTypeReference<PageResponse<PassagemDtoDetalhar>>() {
				});

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isNotNull();

		PageResponse<PassagemDtoDetalhar> pageResponse = responseEntity.getBody();

		assertThat(pageResponse.isEmpty()).isFalse();
		assertThat(pageResponse.getContent()).isNotEmpty();
		assertThat(pageResponse.getContent().get(0).id()).isEqualTo(1L);
	}

	@ParameterizedTest
	@MethodSource("parametrosCadastrar")
	@DisplayName("Deveria cadastrar uma passagem com informações válidas")
	void cadastrarCenario1(LocalDateTime timestampCompra, LocalDateTime timestampPartida,
			LocalDateTime timestampChegada, Long idOrigem, Long idDestino, Long idAeronave, Long idPassageiro,
			Double valorPassagem, TipoAssento tipoAssento) {

		PassagemDtoCadastro requestBody = new PassagemDtoCadastro(timestampCompra, timestampPartida, timestampChegada,
				idOrigem, idDestino, idAeronave, idPassageiro, valorPassagem, tipoAssento);

		ResponseEntity<PassagemDtoDetalhar> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody,
				PassagemDtoDetalhar.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(responseEntity.getBody()).isNotNull();

	}

	static Stream<Arguments> parametrosCadastrar() {
		return Stream.of(
				Arguments.of(LocalDateTime.of(2023, 10, 1, 15, 30), LocalDateTime.of(2023, 10, 2, 15, 30),
						LocalDateTime.of(2023, 10, 3, 15, 30), 1L, 2L, 1L, 1L, 200D, TipoAssento.ECONOMICO),
				Arguments.of(LocalDateTime.of(2023, 10, 1, 15, 30), LocalDateTime.of(2023, 10, 2, 15, 30),
						LocalDateTime.of(2023, 10, 3, 15, 30), 1L, 2L, 1L, 1L, 200D, TipoAssento.VIP)

		);
	}

	@ParameterizedTest
	@MethodSource("parametrosCadastroInvalido")
	@DisplayName("Não Deveria cadastrar passagem")
	void cadastrarInvalidoCenario1(LocalDateTime timestampCompra, LocalDateTime timestampPartida,
			LocalDateTime timestampChegada, Long idOrigem, Long idDestino, Long idAeronave, Long idPassageiro,
			Double valorPassagem, TipoAssento tipoAssento, String mensagemDeErro) {

		PassagemDtoCadastro requestBody = new PassagemDtoCadastro(timestampCompra, timestampPartida, timestampChegada,
				idOrigem, idDestino, idAeronave, idPassageiro, valorPassagem, tipoAssento);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody()).contains(mensagemDeErro);

	}

	static Stream<Arguments> parametrosCadastroInvalido() {
		return Stream.of(
				Arguments.of(LocalDateTime.of(2023, 10, 1, 15, 30), LocalDateTime.of(2023, 10, 2, 15, 30),
						LocalDateTime.of(2023, 10, 2, 15, 28), 1L, 2L, 1L, 1L, 200D, TipoAssento.ECONOMICO,
						"A chegada prevista deve ser em uma data/horário após a partida"),
				Arguments.of(LocalDateTime.of(2023, 10, 1, 15, 30), LocalDateTime.of(2023, 10, 2, 15, 30),
						LocalDateTime.of(2023, 10, 2, 15, 30), 1L, 2L, 1L, 1L, 200D, TipoAssento.ECONOMICO,
						"A chegada prevista deve ser em uma data/horário após a partida"),
				Arguments.of(LocalDateTime.of(2023, 10, 1, 15, 30), LocalDateTime.of(2023, 10, 1, 15, 25),
						LocalDateTime.of(2023, 10, 3, 15, 30), 1L, 2L, 1L, 1L, 200D, TipoAssento.VIP,
						"A data/horário da compra deve ser antes da data/horário da partida"),
				Arguments.of(LocalDateTime.of(2023, 10, 1, 15, 30), LocalDateTime.of(2023, 10, 1, 15, 30),
						LocalDateTime.of(2023, 10, 3, 15, 30), 1L, 2L, 1L, 1L, 200D, TipoAssento.VIP,
						"A data/horário da compra deve ser antes da data/horário da partida"),
				Arguments.of(LocalDateTime.of(2023, 10, 1, 15, 30), LocalDateTime.of(2023, 10, 2, 15, 30),
						LocalDateTime.of(2023, 10, 3, 15, 30), 1L, 1L, 1L, 1L, 200D, TipoAssento.ECONOMICO,
						"O Aeroporto de destino tem que ser diferente do de origem"),
				Arguments.of(LocalDateTime.of(2023, 10, 1, 15, 30), LocalDateTime.of(2023, 10, 2, 15, 30),
						LocalDateTime.of(2023, 10, 3, 15, 30), 1L, 2L, 2L, 1L, 200D, TipoAssento.ECONOMICO,
						"Não há assentos disponvieis para essa categoria")

		);
	}

	@Test
	@DisplayName("Deveria detalhar por ID")
	void detalharPorId() {
		Long idExistente = 1L;

		ResponseEntity<PassagemDtoDetalhar> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}",
				HttpMethod.GET, null, PassagemDtoDetalhar.class, idExistente);

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

	private Cidade criarCidade() {

		Pais paisTeste = new Pais("pais teste", "br");
		Estado estadoTeste = new Estado("estado teste", "uf", paisTeste);
		Cidade cidadeTeste = new Cidade("cidade teste", estadoTeste);

		paisRepository.save(paisTeste);
		estadoRepository.save(estadoTeste);
		cidadeRepository.save(cidadeTeste);

		return cidadeTeste;
	}

	@Test
	@DisplayName("Deveria retornar 404 com id invalido ")
	void erro404() {

		Long idNaoExistente = 15L;

		ResponseEntity<String> responseEntity = restTemplate.exchange(URI_PRINCIPAL + "/{id}", HttpMethod.GET, null,
				String.class, idNaoExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

	}

	@ParameterizedTest
	@MethodSource("parametrosIdInvalido")
	@DisplayName("Deveria manda exception ao usar id entidade invalido")
	void cadastrarCenario2(Long idOrigem, Long idDestino, Long aeronave, Long passageiro ) {

		PassagemDtoCadastro requestBody = new PassagemDtoCadastro(LocalDateTime.of(2023, 10, 1, 15, 30), LocalDateTime.of(2023, 10, 2, 15, 30),
				LocalDateTime.of(2023, 10, 3, 15, 30), idOrigem, idDestino, aeronave, passageiro, 200D, TipoAssento.ECONOMICO);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI_PRINCIPAL, requestBody, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	

	}
	
	static Stream<Arguments> parametrosIdInvalido() {
		return Stream.of(

				Arguments.of(15L, 2L, 1L, 1L),

				Arguments.of(1L, 25L, 1L, 1L),
				
				Arguments.of(1L, 2L, 15L, 1L),

				Arguments.of(1L, 2L, 1L, 15L)
				
				);
	}
	
	@Test
	@DisplayName("Não Deveria excluir  se existe entidade vinculada")
	void excluirInvalidoRegistrada() {

		Long idExistente = 1L;

		ResponseEntity<String> responseEntity = restTemplate.exchange("/endereco/{id}", HttpMethod.DELETE, null,
				String.class, idExistente);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getBody()).isNotNull();
		assertThat(responseEntity.getBody())
				.contains("Não é possível excluir devido existir outra entidade vinculada a essa.");
	}
	

}
