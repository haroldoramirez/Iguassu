package br.com.emanuelvictor.iguassu.web.controller;

import java.awt.print.Pageable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import br.com.emanuelvictor.iguassu.web.entity.address.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.emanuelvictor.iguassu.web.entity.Empresa;

import br.com.emanuelvictor.iguassu.web.service.ServiceEmpresa;
import br.com.emanuelvictor.iguassu.web.util.Converter;

//TODO
@Controller
public class ControllerEmpresa {
	@Autowired
	ServiceEmpresa serviceEmpresa;
	

	@RequestMapping(value = "/empresas", method = RequestMethod.POST)
	public @ResponseBody Object salve(@RequestBody Empresa empresa) {
		return this.serviceEmpresa.save(empresa);
    }

	@RequestMapping(value = "/empresas/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
			serviceEmpresa.delete(id);
	}

	@RequestMapping(value = "/empresas/{id}", method = RequestMethod.GET)
	public @ResponseBody Object find(@PathVariable Long id) {
		return serviceEmpresa.find(id);
	}

    @RequestMapping(value = "/empresas", method = RequestMethod.GET)
    public @ResponseBody Object find() {
        return serviceEmpresa.find(new PageRequest(0, 30, Sort.Direction.DESC, "dataDeCadastro"));
    }

	@RequestMapping(value = "/empresas/{inicio}/{fim}/{order}", method = RequestMethod.GET)
	public @ResponseBody Object find(@RequestParam(required = false) String nome,
                                     @RequestParam(required = false) String CNPJ,
                                     @RequestParam(required = false) String rua,
                                     @RequestParam(required = false) String numero,
                                     @RequestParam(required = false) String CEP,
                                     @RequestParam(required = false) String complemento,
                                     @RequestParam(required = false) Long idBairro,
                                     @RequestParam(required = false) Long idCidade,
                                     @RequestParam(required = false) Long idEstado,
                                     @RequestParam(required = false) Long idPais,
                                     @PathVariable Integer inicio, @PathVariable Integer fim, @PathVariable String order) {

		complemento = Converter.enconding(complemento);
		nome = Converter.enconding(nome);
		rua = Converter.enconding(rua);

        Empresa empresa = new Empresa();
        empresa.setNome(nome);
        empresa.setCnpj(CNPJ);
        empresa.setEndereco(new Endereco());
        empresa.getEndereco().setCep(CEP);
        empresa.getEndereco().setNumero(numero);
        empresa.getEndereco().setComplemento(complemento);
        empresa.getEndereco().setRua(rua);

    return serviceEmpresa.find(empresa, idBairro, idCidade, idEstado, idPais, new PageRequest(inicio, fim, Sort.Direction.ASC, order));


	}

}
