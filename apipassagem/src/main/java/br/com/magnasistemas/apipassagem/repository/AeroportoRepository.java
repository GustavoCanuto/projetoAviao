package br.com.magnasistemas.apipassagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.magnasistemas.apipassagem.entity.Aeroporto;
import jakarta.transaction.Transactional;


public interface AeroportoRepository extends JpaRepository<Aeroporto, Long>{
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_aeroporto; ALTER SEQUENCE tb_aeroporto_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

}
