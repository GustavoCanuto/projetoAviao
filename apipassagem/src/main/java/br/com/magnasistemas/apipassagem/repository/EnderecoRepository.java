package br.com.magnasistemas.apipassagem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.magnasistemas.apipassagem.entity.Endereco;
import jakarta.transaction.Transactional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Page<Endereco> findByCidadeId(Long id, Pageable paginacao);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_endereco; ALTER SEQUENCE tb_endereco_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

}
