package br.com.emanuelvictor.iguassu.web.repository.schooling;

import br.com.emanuelvictor.iguassu.web.entity.schooling.CandidatoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by emanuelvictor on 28/09/14.
 */
@Transactional
public interface DAOCandidatoCurso extends JpaRepository<CandidatoCurso, Long> {

    @Transactional(readOnly = true)
    @Query("select c from CandidatoCurso c WHERE ((c.situacaoDoCurso LIKE %:situacaoDoCurso% or :situacaoDoCurso is null) and (c.outrasInformacoes LIKE %:outrasInformacoes% or :outrasInformacoes is null) and (c.periodosConcluidos = :periodosConcluidos or :periodosConcluidos is null) and (c.regime = :regime or :regime is null) and (c.quantidadeDePeriodos = :quantidadeDePeriodos or :quantidadeDePeriodos is null) and (c.curso.id =:curso_id or :curso_id is null) and (c.candidato.id =:candidato_id or :candidato_id is null) and (c.instituicaoDeEnsino.id =:instituicaoDeEnsino_id or :instituicaoDeEnsino_id is null))")
    public List<CandidatoCurso> find(
            @Param("situacaoDoCurso") String situacaoDoCurso,
            @Param("outrasInformacoes") String outrasInformacoes,
            @Param("periodosConcluidos") Integer periodosConcluidos,
            @Param("regime") String regime,
            @Param("quantidadeDePeriodos") Integer quantidadeDePeriodos,
            @Param("curso_id") Long curso_id,
            @Param("candidato_id") Long candidato_id,
            @Param("instituicaoDeEnsino_id") Long instituicaoDeEnsino_id);

    @Transactional(readOnly = true)
    @Query("select c from CandidatoCurso c WHERE c.candidato.id = :candidato_id")
    public List<CandidatoCurso> find(@Param("candidato_id") Long candidato_id);

}
