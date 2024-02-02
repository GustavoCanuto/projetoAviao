package br.com.magnasistemas.apipassagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.magnasistemas.apipassagem.entity.Aeronave;
import jakarta.transaction.Transactional;

public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_aeronave; ALTER SEQUENCE tb_aeronave_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();


}
