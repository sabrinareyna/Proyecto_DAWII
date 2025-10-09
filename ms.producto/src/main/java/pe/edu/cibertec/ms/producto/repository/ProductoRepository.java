package pe.edu.cibertec.ms.producto.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.ms.producto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
