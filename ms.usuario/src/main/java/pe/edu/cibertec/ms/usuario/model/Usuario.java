package pe.edu.cibertec.ms.usuario.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "USUARIO")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODUSUARIO")
    private Long codUsuario;

    @Column(name = "NUMERODOCUMENTO", nullable = false, length = 8)
    private String numeroDocumento;

    @Column(name = "APEUSUARIO", nullable = false, length = 50)
    private String apellido;

    @Column(name = "NOMUSUARIO", nullable = false, length = 50)
    private String nombre;

    @Column(name = "FECNAC", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "SEXUSUARIO", nullable = false, length = 1)
    private String sexo;

    @Column(name = "TELUSUARIO", nullable = false, length = 9)
    private String telefono;

    @Column(name = "CORUSUARIO", nullable = false, length = 80)
    private String correo;

    @Column(name = "CONUSUARIO", nullable = false, length = 100)
    private String contrasenia;

    @Column(name = "FECCRE", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "ESTUSUARIO", nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "CODROL", nullable = false)
    private Rol rol;

}
