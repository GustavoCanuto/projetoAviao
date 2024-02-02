package br.com.magnasistemas.apipassagem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.apipassagem.dto.pais.PaisDtoListagem;
import br.com.magnasistemas.apipassagem.repository.PaisRepository;

@Service
public class PaisService {

	@Autowired
	private PaisRepository repository;

	public List<PaisDtoListagem> listarPaises() {

		return repository.findAll().stream().map(PaisDtoListagem::new).toList();
		
	
	}
}
