package br.com.emanuelvictor.iguassu.web.service.job;

import br.com.emanuelvictor.iguassu.web.entity.job.Cargo;
import br.com.emanuelvictor.iguassu.web.repository.job.DAOCargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceCargo {

	@Autowired
	DAOCargo daoCargo;

	public Cargo save(Cargo cargo) {
		return daoCargo.save(cargo);
	}

	public void delete(Long id) {
		this.daoCargo.delete(id);
	}

	public Cargo find(Long id) {
		return daoCargo.findOne(id);
	}

	public List<Cargo> find() {
		return daoCargo.findAll();
	}

//	public List<Cargo> findNome(String nome) {
//		return daoCargo.findByNomeContaining(nome);
//	}
//
//	public List<Cargo> findDescricao(String descricao) {
//		return daoCargo.findByDescricaoContaining(descricao);
//	}
//
//	public List<Cargo> findNomeDescricao(String nome, String descricao) {
//		return daoCargo.findByNomeContainingAndDescricaoContaining(nome,
//				descricao);
//	}
	
	public List<Cargo> find(String nome, String descricao) {
		return daoCargo.find(nome,descricao);
	}

}