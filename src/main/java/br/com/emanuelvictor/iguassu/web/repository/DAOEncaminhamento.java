package br.com.emanuelvictor.iguassu.web.repository;

import br.com.emanuelvictor.iguassu.web.entity.Empresa;
import br.com.emanuelvictor.iguassu.web.entity.SituacaoEncaminhamento;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.Encaminhamento;

import java.util.Calendar;
import java.util.List;

@Transactional
public interface DAOEncaminhamento extends JpaRepository<Encaminhamento, Long> {

    @Transactional(readOnly = true)
    @Query("select e from Encaminhamento e WHERE ((e.id =:id or :id is null) and (e.observacoes LIKE %:observacoes% or :observacoes is null) and (e.situacao LIKE %:situacao% or :situacao is null) and (e.vaga.id =:vaga_id or :vaga_id is null) and (e.usuario.id =:usuario_id or :usuario_id is null) and (e.candidato.id =:candidato_id or :candidato_id is null) and (e.lancamento.dataDePagamento =:dataDePagamento or :dataDePagamento is null) and (e.lancamento.valor =:valor or :valor is null))")
    public List<Encaminhamento> find(@Param("id") Long id, @Param("observacoes") String observacoes,
                                     @Param("situacao") SituacaoEncaminhamento situacao, @Param("vaga_id") Long vaga_id,
                                     @Param("candidato_id") Long candidato_id, @Param("usuario_id") Long usuario_id,
                                     @Param("dataDePagamento") Calendar dataDePagamento,
                                     @Param("valor") Double valor, Pageable pageable);

    @Transactional(readOnly = true)
    @Query("select e from Encaminhamento e")
    public List<Encaminhamento> find(Pageable pageable);
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