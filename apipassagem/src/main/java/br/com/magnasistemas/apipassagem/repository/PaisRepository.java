package br.com.magnasistemas.apipassagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.magnasistemas.apipassagem.entity.Pais;
import jakarta.transaction.Transactional;

public interface PaisRepository extends JpaRepository<Pais, Long> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_pais; ALTER SEQUENCE tb_pais_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

}
