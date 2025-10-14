package pe.edu.cibertec.ms.usuario.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ROL")
@Data
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODROL")
    private Integer codRol;

    @Column(name = "NOMBREROL", nullable = false, length = 100)
    private String nombreRol;

    @Column(name = "ESTROL", nullable = false)
    private Boolean estRol;

}
