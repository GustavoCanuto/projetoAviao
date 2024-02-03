package br.com.magnasistemas.apipassagem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magnasistemas.apipassagem.dto.endereco.EnderecoDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.endereco.EnderecoDtoDetalhar;
import br.com.magnasistemas.apipassagem.service.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping
	@Transactional
	public ResponseEntity<EnderecoDtoDetalhar> cadastrar(@RequestBody @Valid EnderecoDtoCadastro dados,
			UriComponentsBuilder uriBuilder) {

		var bairro = enderecoService.cadastrarEndereco(dados);

		var uri = uriBuilder.path("/endereco/{id}").buildAndExpand(bairro.id()).toUri();

		return ResponseEntity.created(uri).body(bairro);

	}

	@GetMapping
	public ResponseEntity<Page<EnderecoDtoDetalhar>> listar(
			@PageableDefault(size = 10) Pageable paginacao) {

		return ResponseEntity.ok(enderecoService.listarEnderecos(paginacao));

	}

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDtoDetalhar> detalhar(@PathVariable Long id) {

		return ResponseEntity.ok(enderecoService.detalharBairro(id));
	}

	@GetMapping("/cidade/{id}")
	public ResponseEntity<Page<EnderecoDtoDetalhar>> listarPorCidade(@PathVariable Long id,
			@PageableDefault(size = 10) Pageable paginacao) {

		return ResponseEntity.ok(enderecoService.listarEnderecoPorCidade(id, paginacao));

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EnderecoDtoDetalhar> atualizar(@PathVariable Long id, @RequestBody EnderecoDtoCadastro dados) {

		return ResponseEntity.ok(enderecoService.atualizarCadastro(dados, id));

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		enderecoService.deletaCadastro(id);

		return ResponseEntity.noContent().build();
	}

}