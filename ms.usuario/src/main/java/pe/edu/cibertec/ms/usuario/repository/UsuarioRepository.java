package pe.edu.cibertec.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import pe.edu.cibertec.ms.usuario.model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Procedure("USP_INSERT_USER")
    void registrarUsuario(
            Integer codUsuario,
            String numeroDocumento,
            String apellido,
            String nombre,
            LocalDate fechaNacimiento,
            String sexo,
            String telefono,
            String correo,
            String contrasena,
            LocalDateTime fechaCreacion,
            Boolean estado,
            int RolId
    );
}
