package br.com.magnasistemas.apipassagem.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.magnasistemas.apipassagem.entity.Passagem;
import jakarta.transaction.Transactional;

public interface PassagemRepository extends JpaRepository<Passagem, Long> {

	@Query(value = "SELECT * FROM tb_passagem "
			+ "WHERE fk_origem = :idOrigem "
			+ "AND fk_destino = :idDestino "
			+ "AND timestamp_partida > CURRENT_TIMESTAMP + interval '1 hour'"
			+ "AND fk_aeronave IN ("
			+ "    SELECT id FROM tb_aeronave "
			+ "    WHERE (qtd_assento_economico > 0 OR qtd_assento_vip > 0)"
			+ ")", nativeQuery = true)
	Page<Passagem> findAvailablePassagens(Pageable paginacao, Long idOrigem, Long idDestino);

	@Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Passagem p " +
		       "WHERE (p.idAeronave.id = :aeronaveId " +
		       "AND (p.timestampPartida BETWEEN :inicioIntervalo AND :fimIntervalo " +
		       "OR p.timestampChegada BETWEEN :inicioIntervalo AND :fimIntervalo))")
		boolean existsByAeronaveIdAndTimestampInterval(Long aeronaveId, LocalDateTime inicioIntervalo, LocalDateTime fimIntervalo);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM tb_passagem; ALTER SEQUENCE tb_passagem_id_seq RESTART WITH 1", nativeQuery = true)
	void deleteAllAndResetSequence();

}
