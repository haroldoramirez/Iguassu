package br.com.emanuelvictor.iguassu.web.repository.address;

import java.util.List;

import br.com.emanuelvictor.iguassu.web.entity.address.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.address.Bairro;

@Transactional
public interface DAOBairro extends JpaRepository<Bairro, Long> {

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
//	@Query("select b from Bairro b inner join b.cidade c where c.id = :cidade_id")
//	public List<Bairro> findByIdCidade(@Param("cidade_id") Long cidade_id);
//
//	@Transactional(readOnly = true)
//	public List<Bairro> findByNomeContaining(@Param("nome") String nome);

    @Transactional(readOnly = true)
    @Query("select b from Bairro b  WHERE ((b.nome LIKE %:nome% or :nome is null) and (b.cidade.id = :cidade_id))")
    public Bairro[] findBairrosByNomeBairroAndIdCidade(@Param("nome") String nome, @Param("cidade_id") Long cidade_id);

}