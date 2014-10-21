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

import br.com.emanuelvictor.iguassu.web.entity.Encaminhamento;
import br.com.emanuelvictor.iguassu.web.service.ServiceEncaminhamento;

//TODO
@Controller
public class ControllerEncaminhamento {
	@Autowired
	ServiceEncaminhamento serviceEncaminhamento;

	@RequestMapping(value = "/encaminhamentos", method = RequestMethod.POST)
	public @ResponseBody Encaminhamento salve(@RequestBody Encaminhamento encaminhamento) {
		return this.serviceEncaminhamento.save(encaminhamento);
	}

	@RequestMapping(value = "/encaminhamentos/{id}", method = RequestMethod.GET)
	public @ResponseBody Encaminhamento delete(@PathVariable Long id) {
        return serviceEncaminhamento.find(id);
	}

	@RequestMapping(value = "/encaminhamentos", method = RequestMethod.GET)
        public @ResponseBody Object find() {
                return serviceEncaminhamento.find();
	}


}
