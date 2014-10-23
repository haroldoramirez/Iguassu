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
//        usuario1.setSenha(new BCryptPasswordEncoder().encode("teste"));
//        usuario1.setNome("Fernando");
//        usuario1.setLogin("Fernando");
//        usuario1.setPerfil(Perfil.GERENTE);
//
//        daoUsuario.save(usuario1);
//
//        Usuario usuario2 = new Usuario();
//        usuario2.setNome("Emanuel");
//        usuario2.setSenha(new BCryptPasswordEncoder().encode("teste"));
//        usuario2.setLogin("Emanuel");
//        usuario2.setPerfil(Perfil.ATENDENTE);
//
//        return daoUsuario.save(usuario2);

        Usuario user = daoUsuario.findByLogin(login);
        if (user == null || user.getId() == null || user.getId() == 0){
            throw new UsernameNotFoundException("Tentativa de login sem sucesso, nome de usuário: " + login + " - Nome de usuário não encontrado");
        }
        return user;
    }

    public Usuario getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            try{
                return (Usuario) auth.getPrincipal();
            }catch (Exception e){
                return null;
            }
        else
            return null;
    }
}