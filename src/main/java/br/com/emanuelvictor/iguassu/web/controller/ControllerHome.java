package br.com.emanuelvictor.iguassu.web.controller;
//TODO
import java.util.List;
import java.util.Locale;

import br.com.emanuelvictor.iguassu.web.entity.Candidato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.emanuelvictor.iguassu.web.service.ServiceHome;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class ControllerHome {

	@Autowired
	ServiceHome serviceHome;

//	@RequestMapping(value = "/front-end", method = RequestMethod.GET)
//	public @ResponseBody
//    String home() {
//		return "/WEB-INF/front-end/app/index";
//	}

//    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
//    public String redirect(){
//        return "redirect:/protected/home";
//    }

//    @RequestMapping(value = "/candidatos", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    List<Candidato> find() {
//        return serviceCandidato.find();
//    }


}
