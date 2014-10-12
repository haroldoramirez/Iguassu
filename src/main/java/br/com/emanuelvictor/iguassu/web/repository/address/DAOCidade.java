package br.com.emanuelvictor.iguassu.web.repository.address;

import java.util.List;

import br.com.emanuelvictor.iguassu.web.entity.address.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.address.Cidade;

@Transactional
public interface DAOCidade extends JpaRepository<Cidade, Long> {

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
//	@Query("select c from Cidade c inner join c.estado e where e.id = :estado_id")
//	public List<Cidade> findByIdEstado(@Param("estado_id") Long estado_id);
//
//	@Transactional(readOnly = true)
//	public List<Cidade> findByNomeContaining(@Param("nome") String nome);

    @Transactional(readOnly = true)
    @Query("select c from Cidade c  WHERE ((c.nome LIKE %:nome% or :nome is null) and (c.estado.id = :estado_id))")
    public Cidade[] findCidadesByNomeCidadeAndIdEstado(@Param("nome") String nome, @Param("estado_id") Long estado_id);

}