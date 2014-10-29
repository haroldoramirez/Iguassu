package br.com.emanuelvictor.iguassu.web.service.job.vacancy;

import br.com.emanuelvictor.iguassu.web.entity.SituacaoVaga;
import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;
import br.com.emanuelvictor.iguassu.web.repository.job.vacancy.DAOVaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class ServiceVaga {

	@Autowired
	DAOVaga daoVaga;

	public Vaga save(Vaga vaga) {

		System.out.println("Salvando vaga" + vaga);
		try {
			if (vaga.getEmpresa().getId()==null) {
				vaga.setEmpresa(null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			vaga.setEmpresa(null);
		}
		

		if (vaga.getSituacao() == null) {
			vaga.setSituacao(SituacaoVaga.DISPONIVEL);
		}
		return daoVaga.save(vaga);
	}

	public void delete(Long id) {
		daoVaga.delete(id);
	}

	public List<Vaga> find() {
		return daoVaga.findAll();
	}

	public Vaga find(Long id) {
		return daoVaga.findOne(id);
	}

}