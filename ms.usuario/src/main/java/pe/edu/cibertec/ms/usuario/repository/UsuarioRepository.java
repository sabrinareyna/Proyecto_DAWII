package pe.edu.cibertec.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.ms.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
