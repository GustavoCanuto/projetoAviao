package br.com.magnasistemas.apipassagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.magnasistemas.apipassagem.entity.Passagem;
import jakarta.transaction.Transactional;

public interface PassagemRepository extends JpaRepository<Passagem, Long>{
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_passagem; ALTER SEQUENCE tb_passagem_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();


}
