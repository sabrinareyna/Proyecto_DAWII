package pe.edu.cibertec.ms.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.usuario.model.Rol;
import pe.edu.cibertec.ms.usuario.model.Usuario;
import pe.edu.cibertec.ms.usuario.repository.RolRepository;
import pe.edu.cibertec.ms.usuario.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    public String registrarUsuario(Usuario usuario) {
        // Buscar el rol CLIENTE automáticamente
        Rol rolCliente = rolRepository.findByNombreRol("CLIENTE")
                .orElseThrow(() -> new RuntimeException("Rol CLIENTE no existe en la base de datos"));

        // Asignar rol cliente
        usuario.setRol(rolCliente);

        // Registrar usuario mediante procedimiento almacenado
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

        return "Usuario registrado correctamente como CLIENTE.";
    }
}
