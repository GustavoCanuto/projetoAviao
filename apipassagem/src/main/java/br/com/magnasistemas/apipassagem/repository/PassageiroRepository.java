package br.com.magnasistemas.apipassagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.magnasistemas.apipassagem.entity.Passageiro;
import jakarta.transaction.Transactional;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_passageiro; ALTER SEQUENCE tb_passageiro_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

}
