package br.com.emanuelvictor.iguassu.web.service.job.vacancy;

import br.com.emanuelvictor.iguassu.web.entity.SituacaoVaga;
import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;
import br.com.emanuelvictor.iguassu.web.repository.job.vacancy.DAOVaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

//		System.out.println("Salvando vaga" + vaga);
//		try {
//			if (vaga.getEmpresa().getId()==null) {
//				vaga.setEmpresa(null);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			vaga.setEmpresa(null);
//		}
		

		if (vaga.getSituacao() == null) {
			vaga.setSituacao(SituacaoVaga.DISPONIVEL);
		}
        vaga.setDataDeAlteracao(Calendar.getInstance());
		return daoVaga.save(vaga);
	}

	public void delete(Long id) {
		daoVaga.delete(id);
	}

	public List<Vaga> find() {
		return daoVaga.findAll();
	}

    public List<Vaga> find(Vaga vaga, PageRequest pageRequest) {
        if (vaga.getEndereco().getBairro().getCidade().getEstado().getPais().getId() == null) {
            return daoVaga.find(vaga.getId(), vaga.getSituacao(), vaga.getCargo().getId(),
                    vaga.getSalario(), vaga.getEmpresa().getId(), vaga.getObservacoes(),
                    vaga.getEndereco().getRua(), vaga.getEndereco().getNumero(),
                    vaga.getEndereco().getCep(), vaga.getEndereco().getComplemento(),
                    pageRequest);
        }

        return daoVaga.find(vaga.getId(), vaga.getSituacao(), vaga.getCargo().getId(),
                vaga.getSalario(), vaga.getEmpresa().getId(), vaga.getObservacoes(),
                vaga.getEndereco().getRua(), vaga.getEndereco().getNumero(),
                vaga.getEndereco().getCep(), vaga.getEndereco().getComplemento(),
                vaga.getEndereco().getBairro().getId(),
                vaga.getEndereco().getBairro().getCidade().getId(),
                vaga.getEndereco().getBairro().getCidade().getEstado().getId(),
                vaga.getEndereco().getBairro().getCidade().getEstado().getPais().getId(),
                pageRequest);
    }

	public Vaga find(Long id) {
		return daoVaga.findOne(id);
	}

}