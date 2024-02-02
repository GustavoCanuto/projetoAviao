package br.com.magnasistemas.apipassagem.service.buscador;

public interface BuscarEntidade<T> {
	
	T buscar(Long id);

}
