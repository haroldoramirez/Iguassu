package br.com.emanuelvictor.iguassu.web.service;

import br.com.emanuelvictor.iguassu.web.entity.Perfil;
import br.com.emanuelvictor.iguassu.web.entity.Usuario;
import br.com.emanuelvictor.iguassu.web.repository.DAOUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.Perf;

import java.util.List;

//@Service("serviceUsuario")
@Service
@Transactional
public class ServiceUsuario implements UserDetailsService {

	@Autowired
    DAOUsuario daoUsuario;

//    @Autowired
//    PasswordEncoder passwordEncoder;

	public Usuario save(Usuario usuario) {
        System.out.println(usuario.getSenha());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		return daoUsuario.save(usuario);
	}

	public void delete(Long id) {
		daoUsuario.delete(id);
	}

	public List<Usuario> find() {
		return daoUsuario.findAll();
	}

	public Usuario find(Long id) {
		return daoUsuario.findOne(id);
	}


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Usuario usuario1 = new Usuario();
//        usuario1.setSenha(new BCryptPasswordEncoder().encode("123456"));
//        usuario1.setNome("Fernando");
//        usuario1.setLogin("Fernando");
//        usuario1.setPerfil(Perfil.GERENTE);
//
//        daoUsuario.save(usuario1);
//
//        Usuario usuario2 = new Usuario();
//        usuario2.setNome("Emanuel");
//        usuario2.setSenha(new BCryptPasswordEncoder().encode("123456"));
//        usuario2.setLogin("Emanuel");
//        usuario2.setPerfil(Perfil.ATENDENTE);
//
//        daoUsuario.save(usuario2);
//
//        Usuario usuario3 = new Usuario();
//        usuario3.setNome("Eliandro");
//        usuario3.setSenha(new BCryptPasswordEncoder().encode("123456"));
//        usuario3.setLogin("Eliandro");
//        usuario3.setPerfil(Perfil.ADMINISTRADOR);
//
//        daoUsuario.save(usuario3);
//
//        Usuario usuario4 = new Usuario();
//        usuario4.setNome("Fransisca");
//        usuario4.setSenha(new BCryptPasswordEncoder().encode("123456"));
//        usuario4.setLogin("Fransisca");
//        usuario4.setPerfil(Perfil.BLOQUEADO);
//
//        return daoUsuario.save(usuario4);
//
        Usuario user = daoUsuario.findByLogin(login);
        if (user == null || user.getId() == null || user.getId() == 0){
            throw new UsernameNotFoundException("Tentativa de login sem sucesso, nome de usuário: " + login + " - Nome de usuário não encontrado");
        }
        if (user.getPerfil() == Perfil.BLOQUEADO){
            throw new UsernameNotFoundException("Usuário BLOQUEADO");
        }
        return user;
    }

    public Usuario getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            try{
                Usuario usuario = (Usuario) auth.getPrincipal();
                usuario.setPassword(null);
                return usuario;
            }catch (Exception e){
                return null;
            }
        else
            return null;
    }
}