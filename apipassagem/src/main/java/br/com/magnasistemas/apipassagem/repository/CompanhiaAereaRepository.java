package br.com.magnasistemas.apipassagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.magnasistemas.apipassagem.entity.CompanhiaAerea;
import jakarta.transaction.Transactional;


public interface CompanhiaAereaRepository extends JpaRepository<CompanhiaAerea, Long> {
	
	boolean existsByCnpj(String cpf);

	boolean existsByEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_companhia_aerea; ALTER SEQUENCE tb_companhia_aerea_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

}
