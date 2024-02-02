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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magnasistemas.apipassagem.dto.passageiro.PassageiroAereaDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.passageiro.PassageiroDtoDetalhar;
import br.com.magnasistemas.apipassagem.service.PassageiroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("passageiro")
public class PassageiroController {

	@Autowired
	private PassageiroService aeroportoService;

	@PostMapping
	@Transactional
	public ResponseEntity<PassageiroDtoDetalhar> cadastrar(@RequestBody @Valid PassageiroAereaDtoCadastro dados,
			UriComponentsBuilder uriBuilder) {

		var bairro = aeroportoService.cadastrar(dados);

		var uri = uriBuilder.path("/bairro/{id}").buildAndExpand(bairro.id()).toUri();

		return ResponseEntity.created(uri).body(bairro);

	}

	@GetMapping
	public ResponseEntity<Page<PassageiroDtoDetalhar>> listar(
			@PageableDefault(size = 10) Pageable paginacao) {

		return ResponseEntity.ok(aeroportoService.listar(paginacao));

	}

	@GetMapping("/{id}")
	public ResponseEntity<PassageiroDtoDetalhar> detalhar(@PathVariable Long id) {

		return ResponseEntity.ok(aeroportoService.detalhar(id));
	}



	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		aeroportoService.deletaCadastro(id);

		return ResponseEntity.noContent().build();
	}
	
}