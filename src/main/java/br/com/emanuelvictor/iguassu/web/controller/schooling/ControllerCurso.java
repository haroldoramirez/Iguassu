package br.com.emanuelvictor.iguassu.web.controller.schooling;

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

import br.com.emanuelvictor.iguassu.web.entity.schooling.Curso;
import br.com.emanuelvictor.iguassu.web.service.schooling.ServiceCurso;
import br.com.emanuelvictor.iguassu.web.util.Converter;

@Controller
public class ControllerCurso {
	@Autowired
	ServiceCurso serviceCurso;

	// Paises
	@RequestMapping(value = "/cursos", method = RequestMethod.POST)
	public @ResponseBody Object salve(@RequestBody Curso curso) {
			return this.serviceCurso.save(curso);
	}

	@RequestMapping(value = "/cursos", method = RequestMethod.GET)
	public @ResponseBody Object find(
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String descricao,
			@RequestParam(required = false) Long idCategoria) {
			return serviceCurso.find(nome, descricao, idCategoria);
	}

	@RequestMapping(value = "/cursos/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
        serviceCurso.delete(id);
	}

}
