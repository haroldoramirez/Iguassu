package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.Candidato;
import br.com.emanuelvictor.iguassu.web.entity.SituacaoDoCandidato;
import br.com.emanuelvictor.iguassu.web.entity.job.Experiencia;
import br.com.emanuelvictor.iguassu.web.entity.schooling.CandidatoCurso;
import br.com.emanuelvictor.iguassu.web.repository.DAOCandidato;
import br.com.emanuelvictor.iguassu.web.repository.DAOLancamento;
import br.com.emanuelvictor.iguassu.web.repository.job.DAOExperiencia;
import br.com.emanuelvictor.iguassu.web.repository.schooling.DAOCandidatoCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceCandidato {
    // TODO o service experiencia estará dentro de candidato
    @Autowired
    DAOCandidatoCurso daoCandidatoCurso;

    @Autowired
    DAOExperiencia daoExperiencia;

    @Autowired
    DAOCandidato daoCandidato;

    @Autowired
    DAOLancamento daoLancamento;


    public Candidato save(Candidato candidato) {
        if (candidato.getSituacao() == null) {
            candidato.setSituacao(SituacaoDoCandidato.BLOQUEADO);
        }
        return daoCandidato.save(candidato);
    }

    public List<Candidato> find() {
        return daoCandidato.findAll();
    }

    public Candidato find(Long id) {
        return daoCandidato.findOne(id);
    }


    public Experiencia save(Experiencia experiencia) {
        return daoExperiencia.save(experiencia);
    }

    public CandidatoCurso save(CandidatoCurso candidatoCurso) {
        return daoCandidatoCurso.save(candidatoCurso);
    }

    public List<Experiencia> findExperiencias(Long id) {
        return daoExperiencia.find(id);
    }

    public List<CandidatoCurso> findCursos(Long id) {
        return daoCandidatoCurso.find(id);
    }

    public void deleteExperiencia(Long id){
        this.daoExperiencia.delete(id);
    }

    public void deleteCurso(Long id){
        this.daoCandidatoCurso.delete(id);
    }

}