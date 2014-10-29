package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.Candidato;
import br.com.emanuelvictor.iguassu.web.entity.Lancamento;
import br.com.emanuelvictor.iguassu.web.entity.SituacaoCandidato;
import br.com.emanuelvictor.iguassu.web.entity.TipoLancamento;
import br.com.emanuelvictor.iguassu.web.entity.job.Experiencia;
import br.com.emanuelvictor.iguassu.web.entity.schooling.CandidatoCurso;
import br.com.emanuelvictor.iguassu.web.repository.DAOCandidato;
import br.com.emanuelvictor.iguassu.web.repository.DAOLancamento;
import br.com.emanuelvictor.iguassu.web.repository.job.DAOExperiencia;
import br.com.emanuelvictor.iguassu.web.repository.schooling.DAOCandidatoCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
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
        if (candidato.getId() == null) {
            candidato.setSituacao(SituacaoCandidato.BLOQUEADO);
            Lancamento lancamento = new Lancamento();
            lancamento.setValor(30.00);
            lancamento.setDataDeVencimento(Calendar.getInstance());
            lancamento.setTipoLancamento(TipoLancamento.ENTRADA);
            lancamento.setDescricao("Cadastro de candidato");
            candidato = daoCandidato.save(candidato);
            lancamento.setPessoa(candidato);
            this.daoLancamento.save(lancamento);
            return candidato;
        }else if (candidato.getSituacao()== SituacaoCandidato.DISPONIVEL){
            Lancamento lancamento = this.daoLancamento.getByIdPessoa(candidato.getId()).getLast();
            if (lancamento!=null){
                lancamento.setDataDePagamento(Calendar.getInstance());
                this.daoLancamento.save(lancamento);
            }
            return daoCandidato.save(candidato);
        }else{
            candidato.setSituacao(this.daoCandidato.findOne(candidato.getId()).getSituacao());
            return this.daoCandidato.save(candidato);
        }

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