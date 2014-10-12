package br.com.emanuelvictor.iguassu.web.service.schooling;

import br.com.emanuelvictor.iguassu.web.entity.schooling.CategoriaCurso;
import br.com.emanuelvictor.iguassu.web.repository.schooling.DAOCategoriaCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceCategoriaCurso {

	@Autowired
	DAOCategoriaCurso daoCategoriaCurso;

	public CategoriaCurso save(CategoriaCurso categoriaCurso) {
		return daoCategoriaCurso.save(categoriaCurso);
	}

	public void delete(Long id) {
		this.daoCategoriaCurso.delete(id);
	}

	public CategoriaCurso find(Long id) {
		return daoCategoriaCurso.findOne(id);
	}

	public List<CategoriaCurso> find() {
		return daoCategoriaCurso.findAll();
	}

	public List<CategoriaCurso> find(String nome) {
		return daoCategoriaCurso.find(nome);
	}
	//
	// public List<CategoriaCurso> findDescricao(String descricao) {
	// return daoCurso.findByDescricaoContaining(descricao);
	// }
	//
	// public List<CategoriaCurso> findNomeDescricao(String nome, String
	// descricao) {
	// return daoCurso.findByNomeContainingAndDescricaoContaining(nome,
	// descricao);
	// }

	// public List<CategoriaCurso> find(String nome, String descricao) {
	// return daoCategoriaCurso.find(nome,descricao);
	// }

}