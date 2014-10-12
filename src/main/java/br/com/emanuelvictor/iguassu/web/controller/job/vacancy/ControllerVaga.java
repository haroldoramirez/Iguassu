package br.com.emanuelvictor.iguassu.web.controller.job.vacancy;

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

import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;
import br.com.emanuelvictor.iguassu.web.service.job.vacancy.ServiceVaga;

//TODO
@Controller
public class ControllerVaga {
	@Autowired
	ServiceVaga serviceVaga;

	@RequestMapping(value = "/vagas", method = RequestMethod.POST)
	public @ResponseBody Object salve(@RequestBody Vaga vaga) {

			return this.serviceVaga.save(vaga);

	}

	@RequestMapping(value = "/vagas/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
        serviceVaga.delete(id);
	}

	@RequestMapping(value = "/vagas", method = RequestMethod.GET)
	public @ResponseBody Object find() {
			return serviceVaga.find();
	}

}
