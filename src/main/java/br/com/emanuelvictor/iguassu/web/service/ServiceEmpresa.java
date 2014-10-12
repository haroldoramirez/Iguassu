package br.com.emanuelvictor.iguassu.web.service;


import br.com.emanuelvictor.iguassu.web.entity.Empresa;
import br.com.emanuelvictor.iguassu.web.repository.DAOEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceEmpresa {

	@Autowired
	DAOEmpresa daoEmpresa;

	public Empresa save(Empresa empresa) {
		return daoEmpresa.save(empresa);
	}

	public void delete(Long id) {
		daoEmpresa.delete(id);
	}

	public List<Empresa> find() {
		return daoEmpresa.findAll();
	}

	// own attributes

	public Empresa find(Long id) {
		return daoEmpresa.findOne(id);
	}

	public List<Empresa> find(String nome, String cnpj, String rua,
			String numero, String cep, String complemento, Long idBairro,
			Long idCidade, Long idEstado, Long idPais) {
		System.out.println("Buscando por " + nome + cnpj + rua + numero + cep
				+ complemento);
		if ((idBairro == null) && (idCidade == null) && (idEstado == null)
				&& (idPais == null)) {
			return daoEmpresa.find(nome, cnpj, rua, numero, cep, complemento);
		}
		return daoEmpresa.find(nome, cnpj, rua, numero, cep, complemento,
				idBairro, idCidade, idEstado, idPais);

	}

}