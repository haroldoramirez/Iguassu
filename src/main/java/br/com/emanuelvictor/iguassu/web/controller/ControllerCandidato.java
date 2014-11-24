package br.com.emanuelvictor.iguassu.web.controller;

import br.com.emanuelvictor.iguassu.web.entity.Candidato;
import br.com.emanuelvictor.iguassu.web.entity.Empresa;
import br.com.emanuelvictor.iguassu.web.entity.Lancamento;
import br.com.emanuelvictor.iguassu.web.entity.job.Experiencia;
import br.com.emanuelvictor.iguassu.web.entity.schooling.CandidatoCurso;
import br.com.emanuelvictor.iguassu.web.service.ServiceCandidato;
import br.com.emanuelvictor.iguassu.web.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//Camada responsável apenas pelas URL's
@Controller
public class ControllerCandidato {

    @Autowired
    ServiceUsuario serviceUsuario;

	@Autowired
	ServiceCandidato serviceCandidato;

	@RequestMapping(value = "/candidatos", method = RequestMethod.POST)
	public @ResponseBody
	Candidato save(@RequestBody Candidato candidato) {
        return this.serviceCandidato.save(candidato, this.serviceUsuario.getCurrentUser());
	}

    @RequestMapping(value = "/candidatos/{id}/renovar/contrato", method = RequestMethod.GET)
    public @ResponseBody
    Candidato renovarContrato(@PathVariable("id") Long id) throws Exception{
        return this.serviceCandidato.renovarContrato(id, this.serviceUsuario.getCurrentUser());
    }

    @RequestMapping(value = "/app/candidatos/{id}/foto", method = RequestMethod.POST)
    public @ResponseBody Candidato uploadFoto(@PathVariable("id") String id, @RequestPart("file") MultipartFile file) {
        return this.serviceCandidato.uploadPhoto(id, file);
    }

    @RequestMapping(value = "/candidatos/{pagina}", method = RequestMethod.POST)
    public @ResponseBody Object find(@RequestBody(required = false) Candidato candidato,
                                     @PathVariable Integer pagina) {
        return serviceCandidato.find(candidato, new PageRequest(pagina,20));
    }


	@RequestMapping(value = "/candidatos/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Candidato find(@PathVariable Long id) {
		return serviceCandidato.find(id);
	}

	@RequestMapping(value = "/candidatos", method = RequestMethod.GET)
	public @ResponseBody
	List<Candidato> find() {
		return serviceCandidato.find();
	}

    @RequestMapping(value = "/candidatos/{id}/contrato", method = RequestMethod.GET)
    public @ResponseBody String[] contrato(@PathVariable Long id) throws Exception{
        return serviceCandidato.contrato(id);
    }

	// ---- experiencias

	@RequestMapping(value = "/candidatos/experiencias", method = RequestMethod.POST)
	public @ResponseBody
	Experiencia save(@RequestBody Experiencia experiencia) {
		return this.serviceCandidato.save(experiencia);
	}

	@RequestMapping(value = "/candidatos/{id}/experiencias", method = RequestMethod.GET)
	public @ResponseBody
	List<Experiencia> findExperiencias(@PathVariable Long id) {
		return serviceCandidato.findExperiencias(id);
	}

	@RequestMapping(value = "/candidatos/experiencias/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	void deleteExperiencia(@PathVariable Long id) {
		serviceCandidato.deleteExperiencia(id);
	}

	// ---- curso

	@RequestMapping(value = "/candidatos/cursos", method = RequestMethod.POST)
	public @ResponseBody
	Object save(@RequestBody CandidatoCurso candidatoCurso) {
		return this.serviceCandidato.save(candidatoCurso);
	}

	@RequestMapping(value = "/candidatos/{id}/cursos", method = RequestMethod.GET)
	public @ResponseBody
	List<CandidatoCurso> findCursos(@PathVariable Long id) {
		return serviceCandidato.findCursos(id);
	}

    @RequestMapping(value = "/candidatos/{id}/lancamentos", method = RequestMethod.GET)
    public @ResponseBody
    String[] getLancamentos(@PathVariable Long id) throws Exception {
        return serviceCandidato.getLancamentosString(id);
    }

	@RequestMapping(value = "/candidatos/cursos/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	void deleteCurso(@PathVariable Long id) {
		serviceCandidato.deleteCurso(id);
	}

}
