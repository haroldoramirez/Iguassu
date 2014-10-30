package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.Lancamento;
import br.com.emanuelvictor.iguassu.web.repository.DAOLancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceLancamento {

	@Autowired
	DAOLancamento daoLancamento;


	public Lancamento save(Lancamento lancamento) {
        return daoLancamento.save(lancamento);
	}

	public void delete(Long id) {
		daoLancamento.delete(id);
	}

	public List<Lancamento> find() {
		return daoLancamento.findAll();
	}

	public Lancamento find(Long id) {
		return daoLancamento.findOne(id);
	}

}