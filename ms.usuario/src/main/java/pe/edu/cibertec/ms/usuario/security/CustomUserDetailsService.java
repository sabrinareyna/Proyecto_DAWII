package pe.edu.cibertec.ms.usuario.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.usuario.model.Usuario;
import pe.edu.cibertec.ms.usuario.repository.UsuarioRepository;

import java.util.Collections;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));
        Set<GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority(usuario.getRol().getName())
        );
        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreo(), usuario.getContrasena(), authorities);
    }
}