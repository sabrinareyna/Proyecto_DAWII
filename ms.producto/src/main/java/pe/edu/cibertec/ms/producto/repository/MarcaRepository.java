package pe.edu.cibertec.ms.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.ms.pedido.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
