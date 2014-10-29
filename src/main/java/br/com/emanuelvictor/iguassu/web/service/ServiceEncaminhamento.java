package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.*;
import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;
import br.com.emanuelvictor.iguassu.web.repository.DAOCandidato;
import br.com.emanuelvictor.iguassu.web.repository.DAOEncaminhamento;
import br.com.emanuelvictor.iguassu.web.repository.DAOLancamento;
import br.com.emanuelvictor.iguassu.web.repository.job.vacancy.DAOVaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class ServiceEncaminhamento {

    @Autowired
    DAOLancamento daoLancamento;

	@Autowired
	DAOEncaminhamento daoEncaminhamento;

    @Autowired
    DAOVaga daoVaga;

    @Autowired
    DAOCandidato daoCandidato;

	public Encaminhamento save(Encaminhamento encaminhamento) {
        // Se o usuário estiver bloqueado ele não salva
        if (encaminhamento.getCandidato().getSituacao() == SituacaoCandidato.BLOQUEADO){
            return null;
        }

		if (encaminhamento.getId() == null) { // Novo encaminhamento
			encaminhamento.setSituacao(SituacaoEncaminhamento.ANDAMENTO);
            return daoEncaminhamento.save(encaminhamento);
		}else if (encaminhamento.getSituacao() == SituacaoEncaminhamento.SUCESSO){
            // Deve gerar conta a receber para daqui 6 meses, com base em 30 por cento do salário que o candidato irá receber
            Candidato candidato = encaminhamento.getCandidato();
            candidato.setSituacao(SituacaoCandidato.EMPREGADO);
            candidato = this.daoCandidato.save(candidato);
            Vaga vaga = encaminhamento.getVaga();
            vaga.setSituacao(SituacaoVaga.OCUPADA);
            vaga = this.daoVaga.save(vaga);

            Lancamento lancamento = new Lancamento();
            lancamento.setValor(vaga.getSalario()/3);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 290);
            lancamento.setDataDeVencimento(calendar);

            lancamento.setTipoLancamento(TipoLancamento.ENTRADA);
            lancamento.setDescricao("Encaminhamento de candidato");
            lancamento.setPessoa(candidato);
            this.daoLancamento.save(lancamento);

            return daoEncaminhamento.save(encaminhamento);
        }else {// Handler de falha
            return daoEncaminhamento.save(encaminhamento);
        }
	}

	public void delete(Long id) {
		daoEncaminhamento.delete(id);
	}

	public List<Encaminhamento> find() {
		return daoEncaminhamento.findAll();
	}

	public Encaminhamento find(Long id) {
		return daoEncaminhamento.findOne(id);
	}

}