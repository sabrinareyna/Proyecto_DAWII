package pe.edu.cibertec.ms.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.ms.producto.model.Marca;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    @Query(value = "CALL USP_GET_MARCA()", nativeQuery = true)
    List<Marca> getMarcas();

    @Query(value = "CALL USP_SELECT_MARCAS()", nativeQuery = true)
    List<Object[]> selectMarcas();

    @Query(value = "CALL USP_GET_ID_MARCA(:codMarca)", nativeQuery = true)
    Marca getMarcaById(@Param("codMarca") int codMarca);
}
