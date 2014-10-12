package br.com.emanuelvictor.iguassu.web.service.schooling;

import br.com.emanuelvictor.iguassu.web.entity.schooling.Curso;
import br.com.emanuelvictor.iguassu.web.repository.schooling.DAOCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceCurso {

	@Autowired
	DAOCurso daoCurso;

	public Curso save(Curso curso) {
		return daoCurso.save(curso);
	}

	public void delete(Long id) {
		this.daoCurso.delete(id);
	}

	public Curso find(Long id) {
		return daoCurso.findOne(id);
	}

	public List<Curso> find() {
		return daoCurso.findAll();
	}

	public List<Curso> find(String nome, String descricao, Long idCategoriaCurso) {
		return daoCurso.find(nome, descricao, idCategoriaCurso);
	}

}