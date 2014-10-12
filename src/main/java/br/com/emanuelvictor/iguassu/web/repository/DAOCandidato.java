package br.com.emanuelvictor.iguassu.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.Candidato;

@Transactional
public interface DAOCandidato extends JpaRepository<Candidato, Long> {

	// @Transactional(readOnly = true)
	// @Query("select c from Candidato c where c.nome LIKE %?1%")
	// public List<Candidato> getByName(String descricao);

	// @Transactional(readOnly = true)
	// @Query("select c from Candidato c where c.nome like %?1%")
	// public List<Candidato> getByCandidato(String nome);

	// public List<Candidato> findByNomeContaining(String nome);

	// @Transactional(readOnly = true)
	// @Query("select c from Candidato c where c.nome LIKE %:nome% and (c.descricao LIKE %:descricao% or c.descricao is null)")
	// public List<Candidato> getByCandidato(@Param("nome") String nome);

	// public Candidato save(Candidato charge);
	//
	// public void delete(Candidato charge);
	//
	// public void delete(Integer id);
	//
	// @Transactional(readOnly = true)
	// public Candidato find(Candidato charge);
	//
	// @Transactional(readOnly = true)
	// public Candidato find(Integer id);
	//
	// @Transactional(readOnly = true)
	// public List<Candidato> list(Candidato charge);

}