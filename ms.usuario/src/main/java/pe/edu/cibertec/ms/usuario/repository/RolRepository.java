package pe.edu.cibertec.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.ms.usuario.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
