package br.com.emanuelvictor.iguassu.web.service.address;

import br.com.emanuelvictor.iguassu.web.entity.address.Bairro;
import br.com.emanuelvictor.iguassu.web.entity.address.Cidade;
import br.com.emanuelvictor.iguassu.web.entity.address.Estado;
import br.com.emanuelvictor.iguassu.web.entity.address.Pais;
import br.com.emanuelvictor.iguassu.web.entity.base.BaseEntity;
import br.com.emanuelvictor.iguassu.web.repository.address.DAOBairro;
import br.com.emanuelvictor.iguassu.web.repository.address.DAOCidade;
import br.com.emanuelvictor.iguassu.web.repository.address.DAOEstado;
import br.com.emanuelvictor.iguassu.web.repository.address.DAOPais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceEndereco {

	// PAISES

	@Autowired
	DAOPais daoPais;

	public Pais save(Pais pais)  {
		return daoPais.save(pais);
	}

	public void deletePais(Long id)  {
		this.daoPais.delete(id);
	}

	public Pais findPaisById(Long id) {
		return daoPais.findOne(id);
	}


	public Pais[] findPaisesByNomePais(String nome) {
        return daoPais.findPaisesByNomePais(nome);
	}

	// ESTADO

	@Autowired
	DAOEstado daoEstado;

	public Estado save(Estado estado)  {
		return daoEstado.save(estado);
	}

	public void delete(Long id)  {
		this.daoEstado.delete(id);
	}

	public Estado findEstadoByIdEstado(Long id)  {
		return daoEstado.findOne(id);
	}

	public Estado[] findEstadosByNomeEstadoAndIdPais(String nome, Long idPais)  {
		return daoEstado.findEstadosByNomeEstadoAndIdPais(nome, idPais);
	}


	// CIDADE

	@Autowired
	DAOCidade daoCidade;

	public Cidade save(Cidade cidade)  {
		return daoCidade.save(cidade);
	}

	public void deleteCidade(Long id)  {
		this.daoCidade.delete(id);
	}

	public Cidade findCidadeById(Long id)  {
		return daoCidade.findOne(id);
	}

	public Cidade[] findCidadesByNomeCidadeAndIdEstado(String nome, Long idEstado)  {
		return daoCidade.findCidadesByNomeCidadeAndIdEstado(nome,idEstado);
	}


	// BAIRROS

	@Autowired
	DAOBairro daoBairro;

	public Bairro save(Bairro bairro)  {
		return daoBairro.save(bairro);
	}

	public void deleteBairro(Long id)  {
		this.daoBairro.delete(id);
	}

	public Bairro findBairroById(Long id)  {
		return daoBairro.findOne(id);
	}

	public Bairro[] findBairrosByNomeBairroAndIdCidade(String nome, Long idCidade)  {
		return daoBairro.findBairrosByNomeBairroAndIdCidade(nome,idCidade);
	}

}