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

import br.com.magnasistemas.apipassagem.dto.passageiro.PassageiroDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.passageiro.PassageiroDtoDetalhar;
import br.com.magnasistemas.apipassagem.service.PassageiroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("passageiro")
public class PassageiroController {

	@Autowired
	private PassageiroService passageiroService;

	@PostMapping
	@Transactional
	public ResponseEntity<PassageiroDtoDetalhar> cadastrar(@RequestBody @Valid PassageiroDtoCadastro dados,
			UriComponentsBuilder uriBuilder) {

		var dto = passageiroService.cadastrar(dados);

		var uri = uriBuilder.path("/passageiro/{id}").buildAndExpand(dto.id()).toUri();

		return ResponseEntity.created(uri).body(dto);

	}

	@GetMapping
	public ResponseEntity<Page<PassageiroDtoDetalhar>> listar(
			@PageableDefault(size = 10) Pageable paginacao) {

		return ResponseEntity.ok(passageiroService.listar(paginacao));

	}

	@GetMapping("/{id}")
	public ResponseEntity<PassageiroDtoDetalhar> detalhar(@PathVariable Long id) {

		return ResponseEntity.ok(passageiroService.detalhar(id));
	}



	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		passageiroService.deletaCadastro(id);

		return ResponseEntity.noContent().build();
	}
	
}