package br.com.emanuelvictor.iguassu.web.repository.schooling;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.schooling.CategoriaCurso;

@Transactional
public interface DAOCategoriaCurso extends JpaRepository<CategoriaCurso, Long> {

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
	@Query("select c from CategoriaCurso c WHERE (c.nome LIKE %:nome% or :nome is null)")
	public List<CategoriaCurso> find(@Param("nome") String nome);

}