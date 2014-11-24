package br.com.emanuelvictor.iguassu.web.service;


import br.com.emanuelvictor.iguassu.web.entity.Empresa;
import br.com.emanuelvictor.iguassu.web.repository.DAOEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class ServiceEmpresa {

    @Autowired
    DAOEmpresa daoEmpresa;

    public Empresa save(Empresa empresa) {
        empresa.setDataDeAlteracao(Calendar.getInstance());
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

//    public List<Empresa> find(PageRequest pageRequest) {
//        return daoEmpresa.find(pageRequest);
//    }

    public List<Empresa> find(Empresa empresa, PageRequest pageRequest) {

        if (empresa.getEndereco().getBairro().getCidade().getEstado().getPais().getId() == null) {
            return daoEmpresa.find(empresa.getId(), empresa.getNome(), empresa.getEmail(),
                    empresa.getTelefoneResidencial(), empresa.getTelefoneComercial(), empresa.getCnpj(),
                    empresa.getEndereco().getRua(), empresa.getEndereco().getNumero(),
                    empresa.getEndereco().getCep(), empresa.getEndereco().getComplemento(),
                    pageRequest);
        }

        return daoEmpresa.find(empresa.getId(), empresa.getNome(), empresa.getEmail(),
                empresa.getTelefoneResidencial(), empresa.getTelefoneComercial(), empresa.getCnpj(),
                empresa.getEndereco().getRua(), empresa.getEndereco().getNumero(),
                empresa.getEndereco().getCep(), empresa.getEndereco().getComplemento(),
                empresa.getEndereco().getBairro().getId(),
                empresa.getEndereco().getBairro().getCidade().getId(),
                empresa.getEndereco().getBairro().getCidade().getEstado().getId(),
                empresa.getEndereco().getBairro().getCidade().getEstado().getPais().getId(),
                pageRequest);
    }

}