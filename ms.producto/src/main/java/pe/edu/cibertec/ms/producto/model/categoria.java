package pe.edu.cibertec.ms.producto.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="CATEGORIA")
public class categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODCATEGORIA", nullable = false)
    private Long idcat;

    @Column(name = "IMGCATEGORIA", nullable = false)
    private String imgcat;

    @Column(name = "NOMCATEGORIA", nullable = false)
    private String nomcat;

    @Column(name = "ESTCATEGORIA", nullable = false)
    private String estcat;
}}
