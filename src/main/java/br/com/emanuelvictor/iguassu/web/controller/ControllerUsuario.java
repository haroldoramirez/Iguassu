package br.com.emanuelvictor.iguassu.web.controller;

import br.com.emanuelvictor.iguassu.web.entity.Perfil;
import br.com.emanuelvictor.iguassu.web.entity.Usuario;
import br.com.emanuelvictor.iguassu.web.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        usuario = this.serviceUsuario.save(usuario);
        usuario.setSenha(null);
        return usuario;
	}

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.PUT)
    public @ResponseBody Object save(@PathVariable Long id, @RequestBody Usuario usuario) {
        if ((this.serviceUsuario.getCurrentUser().getId() == id)||(this.serviceUsuario.getCurrentUser().getPerfil()== Perfil.GERENTE)){
            if (usuario.getSenha()==null||usuario.getSenha().trim()==""){
                usuario.setSenha(this.serviceUsuario.find(id).getSenha());
            }
            if (this.serviceUsuario.getCurrentUser().getPerfil()!= Perfil.GERENTE){
                usuario.setFilial(this.serviceUsuario.getCurrentUser().getFilial());
                usuario.setPerfil(this.serviceUsuario.find(id).getPerfil());
            }
            usuario = this.serviceUsuario.save(usuario);
            usuario.setSenha(null);
            return usuario;
        }else{
            throw new NaoPermitidoExcpetion();
        }
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public @ResponseBody List<Usuario> find() {
        List<Usuario> usuarios = this.serviceUsuario.find();
        for (int i = 0; i < usuarios.size(); i++) {
            usuarios.get(i).setSenha(null);
        }
        return usuarios;
    }

	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	public @ResponseBody Usuario find(@PathVariable Long id) {
        Usuario usuario = this.serviceUsuario.find(id);
        usuario.setSenha(null);
        return usuario;
	}

    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable Long id) {
        this.serviceUsuario.delete(id);
    }

    @RequestMapping(value = "/usuarios/current", method = RequestMethod.GET)
    public @ResponseBody Object getCurrentUser() {
        Usuario usuario = this.serviceUsuario.getCurrentUser();
        if (usuario == null) throw new NaoLogadoException();
        usuario.setSenha(null);
        return usuario;
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Não logado")
    public class NaoLogadoException extends RuntimeException {
        private static final long serialVersionUID = 1121147605912406759L;
    }

    @ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Não acessível")
    public class NaoPermitidoExcpetion extends RuntimeException {
        private static final long serialVersionUID = 1121141235946406759L;
    }

}
