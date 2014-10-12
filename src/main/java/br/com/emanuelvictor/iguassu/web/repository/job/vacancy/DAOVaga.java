package br.com.emanuelvictor.iguassu.web.repository.job.vacancy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;

@Transactional
public interface DAOVaga extends JpaRepository<Vaga, Long> {

//	@Transactional(readOnly = true)
//	@Query("select e from Empresa e where e.nome LIKE %?1%")
//	public List<Empresa> getByName(String descricao);
//
//	@Transactional(readOnly = true)
//	@Query("select e from Empresa e where e.nome like %?1%")
//	public List<Empresa> getByEmpresa(String nome);
//
//	public List<Empresa> findByNomeContainingAndCNPJContaining(String nome, String CNPJ);
//	
//	@Transactional(readOnly = true)
//	@Query("select e from Empresa e where e.nome LIKE %:nome% and (e.CNPJ LIKE %:CNPJ% or e.CNPJ is null)")
//	public List<Empresa> getByEmpresa(@Param("nome") String nome, @Param("CNPJ") String CNPJ);



}