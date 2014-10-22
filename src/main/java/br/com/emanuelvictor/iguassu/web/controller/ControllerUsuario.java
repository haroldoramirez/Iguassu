package br.com.emanuelvictor.iguassu.web.controller;

import br.com.emanuelvictor.iguassu.web.entity.Usuario;
import br.com.emanuelvictor.iguassu.web.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControllerUsuario {

	@Autowired
    ServiceUsuario serviceUsuario;

	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	public @ResponseBody
    Usuario save(@RequestBody Usuario usuario) {
        return this.serviceUsuario.save(usuario);
	}

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public @ResponseBody List<Usuario> find() {
        return this.serviceUsuario.find();
    }

	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	public @ResponseBody Usuario find(@PathVariable Long id) {
		return this.serviceUsuario.find(id);
	}

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void update(@PathVariable Long id) {
        this.serviceUsuario.delete(id);
    }

    @RequestMapping(value = "/usuarios/current", method = RequestMethod.GET)
    public @ResponseBody Usuario getCurrentUser() {
        return this.serviceUsuario.getCurrentUser();
    }

}
