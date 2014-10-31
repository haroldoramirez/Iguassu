package br.com.emanuelvictor.iguassu.web.service;


import br.com.emanuelvictor.iguassu.web.entity.Empresa;
import br.com.emanuelvictor.iguassu.web.entity.address.Endereco;
import br.com.emanuelvictor.iguassu.web.repository.DAOEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
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

	public List<Empresa> find(Empresa empresa,
                              Long idBairro, Long idCidade,
                              Long idEstado, Long idPais,
                              PageRequest pageRequest) {


        if (idBairro==null && idCidade==null && idEstado==null && idPais==null){
            return daoEmpresa.find(empresa.getNome(),empresa.getCnpj(),
                    empresa.getEndereco().getRua(), empresa.getEndereco().getNumero(),
                    empresa.getEndereco().getCep(), empresa.getEndereco().getComplemento(),
                    pageRequest);
        }

		return daoEmpresa.find(empresa.getNome(), empresa.getCnpj(),
                    empresa.getEndereco().getRua(), empresa.getEndereco().getNumero(),
                    empresa.getEndereco().getCep(), empresa.getEndereco().getComplemento(),
                    idBairro, idCidade, idEstado, idPais, pageRequest);
	}

}