package br.com.emanuelvictor.iguassu.web.controller;

import br.com.emanuelvictor.iguassu.web.entity.Candidato;
import br.com.emanuelvictor.iguassu.web.entity.Lancamento;
import br.com.emanuelvictor.iguassu.web.entity.job.Experiencia;
import br.com.emanuelvictor.iguassu.web.entity.schooling.CandidatoCurso;
import br.com.emanuelvictor.iguassu.web.service.ServiceCandidato;
import br.com.emanuelvictor.iguassu.web.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.util.Iterator;
import java.util.List;

@Controller
public class ControllerCandidato {

    @Autowired
    ServiceUsuario serviceUsuario;

	@Autowired
	ServiceCandidato serviceCandidato;

	@RequestMapping(value = "/candidatos", method = RequestMethod.POST)
	public @ResponseBody
	Candidato save(@RequestBody Candidato candidato) {
        Lancamento lancamento = new Lancamento();
        lancamento.setUsuario(this.serviceUsuario.getCurrentUser());
		return this.serviceCandidato.save(candidato, lancamento);
	}

    @RequestMapping(value = "/app/candidatos/foto/{id}", method = RequestMethod.POST)
    public String upload(@PathVariable Long id, MultipartHttpServletRequest request) {
        System.out.print("fasd");

        Iterator<String> itr=request.getFileNames();

        MultipartFile multiPartFile=request.getFile(itr.next());
//        try{
//            multiPartFile = (MultipartFile) file;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }

        if (!multiPartFile.isEmpty()) {
            try {
                byte[] bytes = multiPartFile.getBytes();

                // Creating the directory to store file
                String rootPath = "/home/emanuelvictor/Projetos/Iguassu/src/main/webapp/app/images"/*System.getProperty("catalina.base")*/;
                System.out.println(" path " + rootPath);
                File dir = new File(rootPath + File.separator + "candidatos");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + id);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                System.out.print("Server File Location="
                        + serverFile.getAbsolutePath());
                return "redirect:#/candidatos";
//                return "You successfully uploaded file=" + name;
            } catch (Exception e) {
                return "You failed to upload " + id + " => " + e.getMessage();
            }
        } else {
            return "redirect:#/candidatos";
//            return "You failed to upload " + name
//                    + " because the file was empty.";
        }
//        return "redirect:#/candidatos";
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

	@RequestMapping(value = "/candidatos/cursos/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	void deleteCurso(@PathVariable Long id) {
		serviceCandidato.deleteCurso(id);
	}

}
