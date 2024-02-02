package br.com.magnasistemas.apipassagem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.magnasistemas.apipassagem.dto.pais.PaisDtoListagem;
import br.com.magnasistemas.apipassagem.service.PaisService;


@RestController
@RequestMapping("pais")
public class PaisController {

	@Autowired
	private PaisService paisService;
	

	@GetMapping
	public ResponseEntity<List<PaisDtoListagem>> listar() {

		return ResponseEntity.ok(paisService.listarPaises());
	}

	
}
