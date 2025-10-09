package pe.edu.cibertec.ms.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.ms.producto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
