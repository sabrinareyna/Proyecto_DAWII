package pe.edu.cibertec.ms.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.ms.pedido.model.Marca;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    // Llama al procedimiento que lista las marcas
    @Query(value = "CALL USP_GET_MARCA()", nativeQuery = true)
    List<Marca> getMarcas();

    // Llama al procedimiento que lista para selects
    @Query(value = "CALL USP_SELECT_MARCAS()", nativeQuery = true)
    List<Object[]> selectMarcas();

    // Llama al procedimiento que obtiene una marca por ID
    @Query(value = "CALL USP_GET_ID_MARCA(:id)", nativeQuery = true)
    Marca getMarcaById(@Param("id") int id);
}
