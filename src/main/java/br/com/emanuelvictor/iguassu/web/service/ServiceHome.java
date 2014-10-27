package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.base.Pessoa;
import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;
import br.com.emanuelvictor.iguassu.web.repository.DAOCandidato;
import br.com.emanuelvictor.iguassu.web.repository.DAOEncaminhamento;
import br.com.emanuelvictor.iguassu.web.repository.DAOLancamento;
import br.com.emanuelvictor.iguassu.web.repository.job.vacancy.DAOVaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceHome {

	@Autowired
	DAOEncaminhamento daoEncaminhamento;

	@Autowired
	DAOCandidato daoCandidato;

	@Autowired
	DAOVaga daoVaga;

	@Autowired
	DAOLancamento daoLancamento;


	public List<Vaga> findVagas(Pessoa pessoa) {
		return daoVaga.findAll();
	}

}