package br.com.emanuelvictor.iguassu.web.controller.job.vacancy;

import br.com.emanuelvictor.iguassu.web.entity.job.vacancy.Vaga;
import br.com.emanuelvictor.iguassu.web.service.job.vacancy.ServiceVaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//TODO
@Controller
public class ControllerVaga {
    @Autowired
    ServiceVaga serviceVaga;

    @RequestMapping(value = "/vagas", method = RequestMethod.POST)
    public
    @ResponseBody
    Object save(@RequestBody Vaga vaga) {
        return this.serviceVaga.save(vaga);
    }

    @RequestMapping(value = "/vagas/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Object find(@PathVariable Long id) {
        return serviceVaga.find(id);
    }

    @RequestMapping(value = "/vagas/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void delete(@PathVariable Long id) {
        serviceVaga.delete(id);
    }

    @RequestMapping(value = "/vagas", method = RequestMethod.GET)
    public
    @ResponseBody
    Object find() {
        return serviceVaga.find();
    }

    @RequestMapping(value = "/vagas/{pagina}", method = RequestMethod.POST)
    public
    @ResponseBody
    Object find(@RequestBody(required = false) Vaga vaga,
                @PathVariable Integer pagina) {
        return serviceVaga.find(vaga, new PageRequest(pagina, 20));
    }

}
