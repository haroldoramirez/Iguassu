package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.Encaminhamento;
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
//		try {
//			if (encaminhamento.getVaga().getId()==null) {
//				encaminhamento.setVaga(null);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			encaminhamento.setVaga(null);
//		}
//		
//		try {
//			if (encaminhamento.getCandidato().getId()==null) {
//				encaminhamento.setCandidato(null);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			encaminhamento.setCandidato(null);
//		}
		
		if (encaminhamento.getDataDeCadastro() == null) {
			encaminhamento.setDataDeCadastro(Calendar.getInstance());
		}
		if (encaminhamento.getSituacao() == null) {
			encaminhamento.setSituacao("Em andamento");
		}
		return daoEncaminhamento.save(encaminhamento);
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