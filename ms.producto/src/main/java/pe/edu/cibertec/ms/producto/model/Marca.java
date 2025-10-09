package pe.edu.cibertec.ms.producto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MARCA")
@Data
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODMARCA", nullable = false)
    private Long id;

    @Column(name = "NOMBREMARCA", nullable = false)
    private String nombre;

    @Column(name = "ESTMARCA", nullable = false)
    private boolean estado;
}
