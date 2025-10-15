package pe.edu.cibertec.ms.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.usuario.model.Usuario;
import pe.edu.cibertec.ms.usuario.repository.UsuarioRepository;

import java.util.List;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public String registrarUsuario(Usuario usuario) {
        usuarioRepository.registrarUsuario(
                0,
                usuario.getNumeroDocumento(),
                usuario.getApellido(),
                usuario.getNombre(),
                usuario.getFechaNacimiento(),
                usuario.getSexo(),
                usuario.getTelefono(),
                usuario.getCorreo(),
                usuario.getContrasena(),
                usuario.getFechaCreacion(),
                usuario.getEstado(),
                usuario.getRol().getCodRol()
        );
        return "Usuario registrado correctamente.";
    }
}
