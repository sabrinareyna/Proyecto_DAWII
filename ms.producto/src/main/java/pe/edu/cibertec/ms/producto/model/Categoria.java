package pe.edu.cibertec.ms.producto.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODCATEGORIA", nullable = false)
    private Long codCategoria;

    @Column(name = "NOMCATEGORIA", nullable = false, length = 100)
    private String nomCategoria;

    @Column(name = "ESTCATEGORIA", nullable = false)
    private boolean estCategoria;
}
