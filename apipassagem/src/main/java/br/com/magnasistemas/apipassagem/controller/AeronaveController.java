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

import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoAtualizar;
import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoCadastro;
import br.com.magnasistemas.apipassagem.dto.aeronave.AeronaveDtoDetalhar;
import br.com.magnasistemas.apipassagem.service.AeronaveService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("aeronave")
public class AeronaveController {

	@Autowired
	private AeronaveService aeronaveService;

	@PostMapping
	@Transactional
	public ResponseEntity<AeronaveDtoDetalhar> cadastrar(@RequestBody @Valid AeronaveDtoCadastro dados,
			UriComponentsBuilder uriBuilder) {

		var dto = aeronaveService.cadastrar(dados);

		var uri = uriBuilder.path("/aeronave/{id}").buildAndExpand(dto.id()).toUri();

		return ResponseEntity.created(uri).body(dto);

	}

	@GetMapping
	public ResponseEntity<Page<AeronaveDtoDetalhar>> listar(
			@PageableDefault(size = 10) Pageable paginacao) {

		return ResponseEntity.ok(aeronaveService.listar(paginacao));

	}

	@GetMapping("/{id}")
	public ResponseEntity<AeronaveDtoDetalhar> detalhar(@PathVariable Long id) {

		return ResponseEntity.ok(aeronaveService.detalhar(id));
	}


	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AeronaveDtoDetalhar> atualizar(@PathVariable Long id, @RequestBody AeronaveDtoAtualizar dados) {

		return ResponseEntity.ok(aeronaveService.atualizarCadastro(dados, id));

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {

		aeronaveService.deletaCadastro(id);

		return ResponseEntity.noContent().build();
	}
	
}
