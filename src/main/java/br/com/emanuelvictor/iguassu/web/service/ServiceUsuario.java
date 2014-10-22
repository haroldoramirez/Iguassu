package br.com.emanuelvictor.iguassu.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.emanuelvictor.iguassu.web.entity.Usuario;
import br.com.emanuelvictor.iguassu.web.repository.DAOUsuario;

@Service("serviceUsuario")
@Transactional
public class ServiceUsuario implements UserDetailsService {

	@Autowired
    DAOUsuario daoUsuario;

    @Autowired
    PasswordEncoder passwordEncoder;

//	public Lancamento save(Usuario usuario) {
//		return daoUsuario.save(usuario);
//	}
//
//	public void delete(Long id) {
//		daoUsuario.delete(id);
//	}
//
//	public List<Lancamento> find() {
//		return daoUsuario.findAll();
//	}
//
//	public Lancamento find(Long id) {
//		return daoUsuario.findOne(id);
//	}


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Usuario usuario = new Usuario();
//        System.out.print("login é" + login);
//        usuario.setLogin("teste");
//        usuario.setNome("teste");
//        usuario.setPerfil(Perfil.ADMINISTRADOR);
//        usuario.setSenha(new BCryptPasswordEncoder().encode("teste"));
//        daoUsuario.save(usuario);
//        return usuario;
        Usuario user = daoUsuario.findByLogin(login);
        if (user == null || user.getId() == null || user.getId() == 0){
            throw new UsernameNotFoundException("Tentativa de login sem sucesso, nome de usuário: " + login + " - Nome de usuário não encontrado");
        }
        return user;
    }

    public Usuario getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null)
            return (Usuario) auth.getPrincipal();
        else
            return null;
    }
}