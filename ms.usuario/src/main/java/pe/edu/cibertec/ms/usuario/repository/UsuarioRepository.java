package pe.edu.cibertec.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.ms.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    @Query(value = "CALL USP_GET_USUARIO_POR_ID(:CODUSUARIO)", nativeQuery = true)
    List<Object[]> getUsuarioInfoById(@Param("CODUSUARIO") Integer codUsuario);
}
