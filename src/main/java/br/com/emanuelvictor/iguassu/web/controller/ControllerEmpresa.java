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
	public @ResponseBody Object save(@RequestBody Empresa empresa) {
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
        return serviceEmpresa.find();
    }

	@RequestMapping(value = "/empresas/{pagina}", method = RequestMethod.POST)
	public @ResponseBody Object find(@RequestBody(required = false) Empresa empresa,
                                     @PathVariable Integer pagina) {
        return serviceEmpresa.find(empresa, new PageRequest(pagina,20));
	}

}
