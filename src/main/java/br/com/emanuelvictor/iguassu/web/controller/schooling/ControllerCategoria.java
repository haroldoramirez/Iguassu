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

import br.com.emanuelvictor.iguassu.web.entity.schooling.CategoriaCurso;
import br.com.emanuelvictor.iguassu.web.service.schooling.ServiceCategoriaCurso;
import br.com.emanuelvictor.iguassu.web.util.Converter;

@Controller
public class ControllerCategoria {
    @Autowired
    ServiceCategoriaCurso serviceCategoriaCurso;

    // Paises
    @RequestMapping(value = "/categoriacursos", method = RequestMethod.POST)
    public
    @ResponseBody
    Object salve(@RequestBody CategoriaCurso categoriaCurso) {
        return this.serviceCategoriaCurso.save(categoriaCurso);
    }

    @RequestMapping(value = "/categoriacursos", method = RequestMethod.GET)
    public
    @ResponseBody
    Object find(
            @RequestParam(required = false) String nome) {
        return serviceCategoriaCurso.find(nome);
    }

    @RequestMapping(value = "/categoriacursos/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void delete(@PathVariable Long id) {
        serviceCategoriaCurso.delete(id);
    }

}
