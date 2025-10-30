package pe.edu.cibertec.ms.usuario.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.ms.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    @Query(value = "CALL USP_GET_USUARIO_POR_ID(:CODUSUARIO)", nativeQuery = true)
    List<Object[]> getUsuarioInfoById(@Param("CODUSUARIO") Integer codUsuario);

    @Modifying
    @Transactional
    @Query(value = "CALL SP_InsertarUsuario(:p_NUMERODOCUMENTO, :p_APEUSUARIO, :p_NOMUSUARIO, :p_FECNAC, :p_SEXUSUARIO, :p_TELUSUARIO, :p_CORUSUARIO, :p_CONUSUARIO)",
            nativeQuery = true)
    void insertarUsuario(
            @Param("p_NUMERODOCUMENTO") String numeroDocumento,
            @Param("p_APEUSUARIO") String apeUsuario,
            @Param("p_NOMUSUARIO") String nomUsuario,
            @Param("p_FECNAC") String fecNac,
            @Param("p_SEXUSUARIO") String sexUsuario,
            @Param("p_TELUSUARIO") String telUsuario,
            @Param("p_CORUSUARIO") String corUsuario,
            @Param("p_CONUSUARIO") String conUsuario
    );

}
