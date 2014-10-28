package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.Encaminhamento;
import br.com.emanuelvictor.iguassu.web.entity.SituacaoCandidato;
import br.com.emanuelvictor.iguassu.web.entity.SituacaoEncaminhamento;
import br.com.emanuelvictor.iguassu.web.repository.DAOEncaminhamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class ServiceEncaminhamento {

	@Autowired
	DAOEncaminhamento daoEncaminhamento;

	public Encaminhamento save(Encaminhamento encaminhamento) {
        if (encaminhamento.getCandidato().getSituacao() == SituacaoCandidato.BLOQUEADO){
            return null;
        }
		if (encaminhamento.getId() == null) {
			encaminhamento.setSituacao(SituacaoEncaminhamento.ANDAMENTO);
            return daoEncaminhamento.save(encaminhamento);
		}else if (encaminhamento.getSituacao() == SituacaoEncaminhamento.SUCESSO){
            return daoEncaminhamento.save(encaminhamento);
        }else {
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