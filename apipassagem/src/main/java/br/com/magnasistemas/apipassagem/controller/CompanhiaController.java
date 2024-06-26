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

import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.companhia.CompanhiaAereaDtoDetalhar;
import br.com.magnasistemas.apipassagem.service.CompanhiaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("companhia")
public class CompanhiaController {

	@Autowired
	private CompanhiaService companhiaService;

	@PostMapping
	@Transactional
	public ResponseEntity<CompanhiaAereaDtoDetalhar> cadastrar(@RequestBody @Valid CompanhiaAereaDtoCadastro dados,
			UriComponentsBuilder uriBuilder) {

		var dto = companhiaService.cadastrar(dados);

		var uri = uriBuilder.path("/companhia/{id}").buildAndExpand(dto.id()).toUri();

		return ResponseEntity.created(uri).body(dto);

	}

	@GetMapping
	public ResponseEntity<Page<CompanhiaAereaDtoDetalhar>> listar(
			@PageableDefault(size = 10) Pageable paginacao) {

		return ResponseEntity.ok(companhiaService.listar(paginacao));

	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanhiaAereaDtoDetalhar> detalhar(@PathVariable Long id) {

		return ResponseEntity.ok(companhiaService.detalhar(id));
	}


	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CompanhiaAereaDtoDetalhar> atualizar(@PathVariable Long id, @RequestBody CompanhiaAereaDtoAtualizar dados) {

		return ResponseEntity.ok(companhiaService.atualizarCadastro(dados, id));

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		companhiaService.deletaCadastro(id);

		return ResponseEntity.noContent().build();
	}
	
}
