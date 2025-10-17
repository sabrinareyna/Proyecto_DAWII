package pe.edu.cibertec.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.ms.usuario.model.Rol;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombreRol(String nombreRol);
}
