package br.com.magnasistemas.apipassagem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.magnasistemas.apipassagem.dto.estado.EstadoDtoListagem;
import br.com.magnasistemas.apipassagem.repository.EstadoRepository;



@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repository;

	public List<EstadoDtoListagem> listarEstados() {

		return repository.findAll().stream().map(EstadoDtoListagem::new).toList();
		
	
	}
}
