package br.com.emanuelvictor.iguassu.web.controller.job;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.emanuelvictor.iguassu.web.entity.job.Cargo;
import br.com.emanuelvictor.iguassu.web.service.job.ServiceCargo;
import br.com.emanuelvictor.iguassu.web.util.Converter;

@Controller
public class ControllerCargo {
	@Autowired
	ServiceCargo serviceCargo;

	// Paises
	@RequestMapping(value = "/cargos", method = RequestMethod.POST)
	public @ResponseBody Object salve(@RequestBody Cargo cargo) {
			return this.serviceCargo.save(cargo);
	}

	@RequestMapping(value = "/cargos", method = RequestMethod.GET)
	public @ResponseBody Object find(
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String descricao) {
			return serviceCargo.find(nome, descricao);
	}

    @RequestMapping(value = "/cargos/{id}", method = RequestMethod.GET)
    public @ResponseBody Object find(@PathVariable Long id) {
        return  serviceCargo.find(id);
    }

	@RequestMapping(value = "/cargos/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
				serviceCargo.delete(id);
	}

}
