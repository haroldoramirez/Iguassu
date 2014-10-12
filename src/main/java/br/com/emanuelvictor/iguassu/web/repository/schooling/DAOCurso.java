package br.com.emanuelvictor.iguassu.web.repository.schooling;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.schooling.Curso;

@Transactional
public interface DAOCurso extends JpaRepository<Curso, Long> {

	// @Transactional(readOnly = true)
	// @Query("select e from Empresa e where e.nome LIKE %?1%")
	// public List<Empresa> getByName(String descricao);
	//
	// @Transactional(readOnly = true)
	// @Query("select e from Empresa e where e.nome like %?1%")
	// public List<Empresa> getByEmpresa(String nome);
	//
	// public List<Empresa> findByNomeContainingAndCNPJContaining(String nome,
	// String CNPJ);
	//
	@Transactional(readOnly = true)
	@Query("select c from Curso c WHERE ((c.nome LIKE %:nome% or :nome is null) and (c.descricao LIKE %:descricao% or :descricao is null) and (c.categoriaCurso.id =:categoriaCurso_id or :categoriaCurso_id is null))")
	public List<Curso> find(@Param("nome") String nome,
			@Param("descricao") String descricao,
			@Param("categoriaCurso_id") Long categoriaCurso_id);

}