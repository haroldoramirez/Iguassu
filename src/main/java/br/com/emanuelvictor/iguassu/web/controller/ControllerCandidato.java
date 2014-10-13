package br.com.emanuelvictor.iguassu.web.controller;

import br.com.emanuelvictor.iguassu.web.entity.Candidato;
import br.com.emanuelvictor.iguassu.web.entity.job.Experiencia;
import br.com.emanuelvictor.iguassu.web.entity.schooling.CandidatoCurso;
import br.com.emanuelvictor.iguassu.web.service.ServiceCandidato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControllerCandidato {

    @Autowired
    ServiceCandidato serviceCandidato;


    @RequestMapping(value = "/candidatos", method = RequestMethod.POST)
    public
    @ResponseBody
    Candidato save(@RequestBody Candidato candidato) {
        return this.serviceCandidato.save(candidato);
    }

    @RequestMapping(value = "/candidatos/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Candidato update(/*@PathVariable Long id,*/ @RequestBody Candidato candidato) {
        return this.serviceCandidato.save(candidato);
    }

    @RequestMapping(value = "/candidatos/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Candidato find(@PathVariable Long id) {
        return serviceCandidato.find(id);
    }

    @RequestMapping(value = "/candidatos", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Candidato> find() {
        return serviceCandidato.find();
    }


    @RequestMapping(value = "/candidatos/experiencias", method = RequestMethod.POST)
    public
    @ResponseBody
    Experiencia save(@RequestBody Experiencia experiencia) {
        return this.serviceCandidato.save(experiencia);
    }

    @RequestMapping(value = "/candidatos/{id}/experiencias", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Experiencia> findExperiencias(@PathVariable Long id) {
        return serviceCandidato.findExperiencias(id);
    }

    @RequestMapping(value = "/candidatos/experiencias/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void deleteExperiencia(@PathVariable Long id) {
        serviceCandidato.deleteExperiencia(id);
    }

    @RequestMapping(value = "/candidatos/cursos", method = RequestMethod.POST)
    public
    @ResponseBody
    Object save(@RequestBody CandidatoCurso candidatoCurso) {
        return this.serviceCandidato.save(candidatoCurso);
    }

    @RequestMapping(value = "/candidatos/{id}/cursos", method = RequestMethod.GET)
    public
    @ResponseBody
    List<CandidatoCurso> findCursos(@PathVariable Long id) {
        return serviceCandidato.findCursos(id);
    }

    @RequestMapping(value = "/candidatos/cursos/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void deleteCurso(@PathVariable Long id) {
        serviceCandidato.deleteCurso(id);
    }


}
