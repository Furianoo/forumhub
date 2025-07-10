package com.forumhub.service;

import com.forumhub.model.Usuario;
import com.forumhub.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Salva um usuário com a senha criptografada.
     * @param usuario instância a ser salva.
     * @return usuário persistido.
     */
    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca um usuário pelo login.
     * @param login login do usuário.
     * @return Optional com o usuário, se existir.
     */
    public Optional<Usuario> buscarPorLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    /**
     * Lista todos os usuários do banco.
     * @return lista de usuários.
     */
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Verifica se existe um usuário com o login informado.
     * @param login login a verificar.
     * @return true se existir, false caso contrário.
     */
    public boolean existsByLogin(String login) {
        return usuarioRepository.findByLogin(login).isPresent();
    }

    /**
     * Remove um usuário pelo ID.
     * @param id identificador do usuário.
     */
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Método necessário para autenticação com Spring Security.
     * Carrega os dados do usuário baseado no login (username).
     * @param username login informado.
     * @return UserDetails com credenciais e autoridades.
     * @throws UsernameNotFoundException se usuário não existir.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return new org.springframework.security.core.userdetails.User(
                usuario.getLogin(),
                usuario.getSenha(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}

