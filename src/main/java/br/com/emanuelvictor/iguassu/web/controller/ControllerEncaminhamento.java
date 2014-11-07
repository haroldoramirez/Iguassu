package br.com.emanuelvictor.iguassu.web.controller;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import br.com.emanuelvictor.iguassu.web.entity.Lancamento;
import br.com.emanuelvictor.iguassu.web.service.ServiceLancamento;
import br.com.emanuelvictor.iguassu.web.service.ServiceUsuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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
    ServiceUsuario serviceUsuario;

	@Autowired
	ServiceEncaminhamento serviceEncaminhamento;

	@RequestMapping(value = "/encaminhamentos", method = RequestMethod.POST)
	public @ResponseBody Encaminhamento save(@RequestBody Encaminhamento encaminhamento) {
        encaminhamento.setUsuario(serviceUsuario.getCurrentUser());
        if (encaminhamento.getLancamento()!=null){
            encaminhamento.getLancamento().setUsuario(serviceUsuario.getCurrentUser());
        }
		return this.serviceEncaminhamento.save(encaminhamento);
	}

    @RequestMapping(value = "/encaminhamentos/{id}/contrato", method = RequestMethod.GET)
    public @ResponseBody String[] contrato(@PathVariable Long id) throws Exception{
       return this.serviceEncaminhamento.contrato(id);
    }

    @RequestMapping(value = "/encaminhamentos", method = RequestMethod.GET)
    public @ResponseBody Object find() {
        return serviceEncaminhamento.find();
    }

	@RequestMapping(value = "/encaminhamentos/{id}", method = RequestMethod.GET)
	public @ResponseBody Encaminhamento find(@PathVariable Long id) {
        return serviceEncaminhamento.find(id);
	}




}
