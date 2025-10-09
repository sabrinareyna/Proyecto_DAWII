package pe.edu.cibertec.ms.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.cibertec.ms.producto.model.Categoria;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query(value = "CALL USP_SELECT_CATEGORIAS()", nativeQuery = true)
    List<Object[]> selectCategorias();
}
