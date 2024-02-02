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

import br.com.magnasistemas.apipassagem.dto.companhiaAerea.CompanhiaAereaDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.companhiaAerea.CompanhiaAereaDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.companhiaAerea.CompanhiaAereaDtoDetalhar;
import br.com.magnasistemas.apipassagem.service.CompanhiaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("companhia")
public class CompanhiaController {

	@Autowired
	private CompanhiaService aeroportoService;

	@PostMapping
	@Transactional
	public ResponseEntity<CompanhiaAereaDtoDetalhar> cadastrar(@RequestBody @Valid CompanhiaAereaDtoCadastro dados,
			UriComponentsBuilder uriBuilder) {

		var bairro = aeroportoService.cadastrar(dados);

		var uri = uriBuilder.path("/bairro/{id}").buildAndExpand(bairro.id()).toUri();

		return ResponseEntity.created(uri).body(bairro);

	}

	@GetMapping
	public ResponseEntity<Page<CompanhiaAereaDtoDetalhar>> listar(
			@PageableDefault(size = 10) Pageable paginacao) {

		return ResponseEntity.ok(aeroportoService.listar(paginacao));

	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanhiaAereaDtoDetalhar> detalhar(@PathVariable Long id) {

		return ResponseEntity.ok(aeroportoService.detalhar(id));
	}


	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CompanhiaAereaDtoDetalhar> atualizar(@PathVariable Long id, @RequestBody CompanhiaAereaDtoAtualizar dados) {

		return ResponseEntity.ok(aeroportoService.atualizarCadastro(dados, id));

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		aeroportoService.deletaCadastro(id);

		return ResponseEntity.noContent().build();
	}
	
}
