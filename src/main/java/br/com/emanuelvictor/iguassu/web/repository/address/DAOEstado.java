package br.com.emanuelvictor.iguassu.web.repository.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.address.Estado;

@Transactional
public interface DAOEstado extends JpaRepository<Estado, Long> {

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

//	@Transactional(readOnly = true)
//	public List<Estado> findByNomeContaining(@Param("nome") String nome);
	
	
//	SELECT pub FROM Publisher pub INNER JOIN pub.magazines mag WHERE pub.revenue > 1000000

//    "select b from Bairro b inner join b.cidade c where c.id = :cidade_id"

	@Transactional(readOnly = true)
	@Query("select e from Estado e  WHERE ((e.nome LIKE %:nome% or :nome is null) and (e.pais.id = :pais_id))")
	public Estado[] findEstadosByNomeEstadoAndIdPais(@Param("nome") String nome, @Param("pais_id") Long pais_id);



	
//	@Transactional(readOnly = true)
//	@Query("select e from Estado e where e.nome LIKE %:nome% and inner join e.pais p where p.id = :pais_id")
//	public List<Estado> findByNomeAndIdPais(@Param("nome") String nome, @Param("pais_id") Long pais_id);
//	@Query("select e from Estado e where e.pais LIKE %:pais_id%")
//	public List<Estado> findByIdPais(@Param("pais_id") Pais pais); 
}