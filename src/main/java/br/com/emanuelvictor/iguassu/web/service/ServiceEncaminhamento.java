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

            // Atualiza o status do candidato para 'EMPREGADO'
            Candidato candidato = encaminhamento.getCandidato();
            candidato.setSituacao(SituacaoCandidato.EMPREGADO);
            candidato = this.daoCandidato.save(candidato);

            // Atualiza o status da vaga para 'OCUPADA'
            Vaga vaga = encaminhamento.getVaga();
            vaga.setSituacao(SituacaoVaga.OCUPADA);
            vaga = this.daoVaga.save(vaga);

            // Deve gerar conta a receber para daqui 1 mes, com base em 30 por cento do salário que o candidato irá receber
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 30);
            Lancamento lancamento = new Lancamento();
            if (encaminhamento.getLancamento()!=null){
                lancamento =encaminhamento.getLancamento();
            }
            lancamento.setValor(vaga.getSalario()/3);
            lancamento.setDataDeVencimento(calendar);
            lancamento.setTipoLancamento(TipoLancamento.ENTRADA);
            lancamento.setDescricao("Encaminhamento do candidato " + candidato.getNome() + " para a vaga de código " + vaga.getId());
            lancamento.setPessoa(candidato);
            lancamento.setUsuario(encaminhamento.getUsuario());

            lancamento = this.daoLancamento.save(lancamento);

            //SETA O LANÇAMENTO NO ENCAMINHAMENTO
            encaminhamento.setLancamento(lancamento);
            return daoEncaminhamento.save(encaminhamento);
        }else {// Handler de falha "SE O ENCAMINHAMENTO É CADASTRADO COMO FALHA, NÃO LANÇA LANÇAMENTO E NEM FAZ NADA, SÓ FICA COMO FALHA"
            return daoEncaminhamento.save(encaminhamento);
        }
	}

    public Lancamento findLancamentoEncaminhamento(Encaminhamento encaminhamento) {
        //TODO
        return daoLancamento.getByIdPessoa(encaminhamento.getCandidato().getId()).getLast();
    }

    public Lancamento pagarLancamentoEncaminhamento(Encaminhamento encaminhamento) {
        //TODO
        Lancamento lancamento = daoLancamento.getByIdPessoa(encaminhamento.getCandidato().getId()).getLast();
        return daoLancamento.save(lancamento);
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