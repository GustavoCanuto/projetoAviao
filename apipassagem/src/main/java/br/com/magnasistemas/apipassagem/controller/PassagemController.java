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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoComprar;
import br.com.magnasistemas.apipassagem.dto.passagem.PassagemDtoDetalhar;
import br.com.magnasistemas.apipassagem.service.PassagemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("passagem")
public class PassagemController {

	@Autowired
	private PassagemService passagemService;

	@PostMapping
	@Transactional
	public ResponseEntity<PassagemDtoDetalhar> cadastrar(@RequestBody @Valid PassagemDtoCadastro dados,
			UriComponentsBuilder uriBuilder) {

		var dto = passagemService.cadastrar(dados);

		var uri = uriBuilder.path("/passagem/{id}").buildAndExpand(dto.id()).toUri();

		return ResponseEntity.created(uri).body(dto);

	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PassagemDtoDetalhar> comprar(@PathVariable Long id, @RequestBody @Valid PassagemDtoComprar dados) {

		return ResponseEntity.ok(passagemService.comprarPassagem(dados, id));

	}

	
	@GetMapping
	public ResponseEntity<Page<PassagemDtoDetalhar>> listarPassagemDisponivel(
			@RequestParam(name = "idOrigem", required = false) Long idOrigem,
			@RequestParam(name = "idDestino", required = false) Long idDestino,
			@PageableDefault(size = 10) Pageable paginacao) {

		return ResponseEntity.ok(passagemService.listarPassagemDisponivel(paginacao, idOrigem,idDestino ));

	}

	@GetMapping("/{id}")
	public ResponseEntity<PassagemDtoDetalhar> detalhar(@PathVariable Long id) {

		return ResponseEntity.ok(passagemService.detalhar(id));
	}



	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		passagemService.deletaCadastro(id);

		return ResponseEntity.noContent().build();
	}
	
}