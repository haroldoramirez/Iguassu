package br.com.emanuelvictor.iguassu.web.controller;

import br.com.emanuelvictor.iguassu.web.entity.Encaminhamento;
import br.com.emanuelvictor.iguassu.web.service.ServiceEncaminhamento;
import br.com.emanuelvictor.iguassu.web.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//TODO
@Controller
public class ControllerEncaminhamento {

    @Autowired
    ServiceUsuario serviceUsuario;

    @Autowired
    ServiceEncaminhamento serviceEncaminhamento;

    @RequestMapping(value = "/encaminhamentos", method = RequestMethod.POST)
    public
    @ResponseBody
    Encaminhamento save(@RequestBody Encaminhamento encaminhamento) throws Exception {
        encaminhamento.setUsuario(serviceUsuario.getCurrentUser());
        if (encaminhamento.getLancamento() != null) {
            encaminhamento.getLancamento().setUsuario(serviceUsuario.getCurrentUser());
        }
        return this.serviceEncaminhamento.save(encaminhamento);
    }

    @RequestMapping(value = "/encaminhamentos/{id}/contrato", method = RequestMethod.GET)
    public
    @ResponseBody
    String[] contrato(@PathVariable Long id) throws Exception {
        return this.serviceEncaminhamento.contrato(id);
    }

    @RequestMapping(value = "/encaminhamentos", method = RequestMethod.GET)
    public
    @ResponseBody
    Object find() {
        return serviceEncaminhamento.find();
    }

    @RequestMapping(value = "/encaminhamentos/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Encaminhamento find(@PathVariable Long id) {
        return serviceEncaminhamento.find(id);
    }

    @RequestMapping(value = "/encaminhamentos/{pagina}", method = RequestMethod.POST)
    public
    @ResponseBody
    Object find(@RequestBody(required = false) Encaminhamento encaminhamento, @PathVariable Integer pagina) {
        return serviceEncaminhamento.find(encaminhamento, new PageRequest(pagina, 20));
    }

    @RequestMapping(value = "/encaminhamentos/naopagos", method = RequestMethod.GET)
    public
    @ResponseBody
    Object encaminhamentosNaoPagos() throws Exception{
        serviceEncaminhamento.getEncaminhamentosNaoPagos();
        return null;
    }

    @RequestMapping(value = "/encaminhamentos/emandamento", method = RequestMethod.GET)
    public
    @ResponseBody
    Object encaminhamentosEmAndamento() throws Exception{
        serviceEncaminhamento.getEncaminhamentosEmAndamento();
        return null;
    }

}
