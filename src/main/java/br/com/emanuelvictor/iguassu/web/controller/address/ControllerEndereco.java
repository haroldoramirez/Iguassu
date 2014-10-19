package br.com.emanuelvictor.iguassu.web.controller.address;

import br.com.emanuelvictor.iguassu.web.entity.address.Bairro;
import br.com.emanuelvictor.iguassu.web.entity.address.Cidade;
import br.com.emanuelvictor.iguassu.web.entity.address.Estado;
import br.com.emanuelvictor.iguassu.web.entity.address.Pais;
import br.com.emanuelvictor.iguassu.web.entity.base.BaseEntity;
import br.com.emanuelvictor.iguassu.web.service.address.ServiceEndereco;
import br.com.emanuelvictor.iguassu.web.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class ControllerEndereco {

    @Autowired
    ServiceEndereco serviceEndereco;

    // Paises
    @RequestMapping(value = "/paises", method = RequestMethod.POST)
    public
    @ResponseBody
    Pais save(@RequestBody Pais pais) {
        return serviceEndereco.save(pais);
    }

    @RequestMapping(value = "/paises/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void deletePais(@PathVariable Long id) {
        serviceEndereco.deletePais(id);
    }

    @RequestMapping(value = "/paises/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Pais findPaisByIdPais(@PathVariable Long id) {
        return serviceEndereco.findPaisById(id);
    }

    @RequestMapping(value = "/paises", method = RequestMethod.GET)
    public
    @ResponseBody
    Pais[] findPaisesByNomePais(/*@PathVariable Pais pais*/ @RequestParam(required = false) String nome) {
        return this.serviceEndereco.findPaisesByNomePais(Converter.enconding(nome));
    }

    // Estados
    @RequestMapping(value = "/estados", method = RequestMethod.POST)
    public
    @ResponseBody
    Estado save(@RequestBody Estado estado) {
        System.out.println("Tentando salvar o ESTADO " + estado.toString());
        return serviceEndereco.save(estado);
    }

    @RequestMapping(value = "/estados/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void deleteEstado(@PathVariable Long id) {
        serviceEndereco.delete(id);
    }

    @RequestMapping(value = "/estados/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Estado findEstadoByIdEstado(@PathVariable Long id) {
        return serviceEndereco.findEstadoByIdEstado(id);
    }

    @RequestMapping(value = "/estados/pais/{idPais}", method = RequestMethod.GET)
    public
    @ResponseBody
    Estado[] findEstadosByNomeAndIdPais(
            @RequestParam(required = false) String nome,
            @PathVariable Long idPais) {
        return this.serviceEndereco.findEstadosByNomeEstadoAndIdPais(nome, idPais);
    }


    // Cidades
    @RequestMapping(value = "/cidades", method = RequestMethod.POST)
    public
    @ResponseBody
    Cidade save(@RequestBody Cidade cidade) {
        return serviceEndereco.save(cidade);
    }

    @RequestMapping(value = "/cidades/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void deleteCidade(@PathVariable Long id) {
        serviceEndereco.deleteCidade(id);
    }

    @RequestMapping(value = "/cidades/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Cidade findCidadeByIdCidade(@PathVariable Long id) {
        return serviceEndereco.findCidadeById(id);
    }

    @RequestMapping(value = "/cidades/estado/{idEstado}", method = RequestMethod.GET)
    public
    @ResponseBody
    Cidade[] findCidadesByNomeCidadeAndIdEstado(
            @RequestParam(required = false) String nome,
            @PathVariable Long idEstado) {
        return this.serviceEndereco.findCidadesByNomeCidadeAndIdEstado(nome, idEstado);
    }


    // Bairros
    @RequestMapping(value = "/bairros", method = RequestMethod.POST)
    public
    @ResponseBody
    Bairro save(@RequestBody Bairro bairro) {
        return serviceEndereco.save(bairro);
    }

    @RequestMapping(value = "/bairros/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void deleteBairro(@PathVariable Long id) {
        serviceEndereco.deleteBairro(id);
    }

    @RequestMapping(value = "/bairros/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Bairro findBairroByIdBairro(@PathVariable Long id) {
        return serviceEndereco.findBairroById(id);
    }



    @RequestMapping(value = "/bairros/cidade/{idCidade}", method = RequestMethod.GET)
    public
    @ResponseBody
    Bairro[] findBairrosByNomeBairroAndIdCidade(
            @RequestParam(required = false) String nome,
            @PathVariable Long idCidade) {
        return this.serviceEndereco
                .findBairrosByNomeBairroAndIdCidade(nome, idCidade);
    }
}
