package br.com.emanuelvictor.iguassu.web.repository.job;

import br.com.emanuelvictor.iguassu.web.entity.job.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by emanuelvictor on 28/09/14.
 */
@Transactional
public interface DAOExperiencia extends JpaRepository<Experiencia, Long> {

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
    @Query("select e from Experiencia e WHERE ((e.observacoes LIKE %:observacoes% or :observacoes is null) and (e.atividades LIKE %:atividades% or :atividades is null) and (e.dataInicio = :dataInicio or :dataInicio is null) and (e.dataTermino = :dataTermino or :dataTermino is null) and (e.candidato.id = :candidato_id or :candidato_id is null) and (e.cargo.id = :cargo_id or :cargo_id is null) and (e.empresa.id = :empresa_id or :empresa_id is null))")
    public List<Experiencia> find(@Param("observacoes") String observacoes,
                                  @Param("atividades") String atividades,
                                  @Param("dataInicio") Calendar dataInicio,
                                  @Param("dataTermino") Calendar dataTermino,
                                  @Param("candidato_id") Long candidato_id,
                                  @Param("cargo_id") Long cargo_id,
                                  @Param("empresa_id") Long empresa_id);

    @Transactional(readOnly = true)
    @Query("select e from Experiencia e WHERE e.candidato.id = :candidato_id")
    public List<Experiencia> find(@Param("candidato_id") Long candidato_id);

}