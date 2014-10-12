package br.com.emanuelvictor.iguassu.web.repository.job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.job.Cargo;

@Transactional
public interface DAOCargo extends JpaRepository<Cargo, Long> {

	@Transactional(readOnly = true)
	@Query("select c from Cargo c WHERE ((c.nome LIKE %:nome% or :nome is null) and (c.descricao LIKE %:descricao% or :descricao is null))")
	public List<Cargo> find(@Param("nome") String nome, @Param("descricao") String descricao);
	
}