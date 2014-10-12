package br.com.emanuelvictor.iguassu.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.emanuelvictor.iguassu.web.entity.Lancamento;
import br.com.emanuelvictor.iguassu.web.service.ServiceLancamento;
//TODO
@Controller
public class ControllerLancamento {
	@Autowired
	ServiceLancamento serviceLancamento;

	@RequestMapping(value = "/lancamentos", method = RequestMethod.POST)
	public @ResponseBody Object salve(@RequestBody Lancamento lancamento) {
		return serviceLancamento.save(lancamento);
	}

	@RequestMapping(value = "/lancamentos/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
        serviceLancamento.delete(id);
	}

	@RequestMapping(value = "/lancamentos", method = RequestMethod.GET)
	public @ResponseBody Object find() {
        return serviceLancamento.find();
	}



}
