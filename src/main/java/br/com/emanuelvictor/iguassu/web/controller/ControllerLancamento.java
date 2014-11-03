package br.com.emanuelvictor.iguassu.web.controller;

import br.com.emanuelvictor.iguassu.web.entity.Lancamento;
import br.com.emanuelvictor.iguassu.web.service.ServiceLancamento;
import br.com.emanuelvictor.iguassu.web.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class ControllerLancamento {

    @Autowired
    ServiceUsuario serviceUsuario;

	@Autowired
	ServiceLancamento serviceLancamento;

	@RequestMapping(value = "/lancamentos", method = RequestMethod.POST)
	public @ResponseBody Object salve(@RequestBody Lancamento lancamento) {
        lancamento.setUsuario(serviceUsuario.getCurrentUser());
		return serviceLancamento.save(lancamento);
	}


	@RequestMapping(value = "/lancamentos/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
        serviceLancamento.delete(id);
	}

	@RequestMapping(value = "/lancamentos", method = RequestMethod.GET)
	public @ResponseBody Object find() {
        return serviceLancamento.find();
	};

    @RequestMapping(value = "/lancamentos/{pagina}", method = RequestMethod.POST)
    public @ResponseBody Object find(@RequestBody(required = false) Date [] datas,
                                     @PathVariable Integer pagina) {
        return serviceLancamento.find(datas[0], datas[1], datas[2], new PageRequest(pagina,5));
    }



}
