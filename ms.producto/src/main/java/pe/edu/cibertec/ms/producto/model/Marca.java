package pe.edu.cibertec.ms.producto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "MARCA")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODMARCA", nullable = false)
    private Long codMarca;

    @Column(name = "NOMBREMARCA", nullable = false)
    private String nombreMarca;

    @Column(name = "ESTMARCA", nullable = false)
    private boolean estMarca;
}
