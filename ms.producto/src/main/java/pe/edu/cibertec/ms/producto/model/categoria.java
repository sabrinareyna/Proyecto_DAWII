package pe.edu.cibertec.ms.producto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CODCATEGORIA;

    private String IMGCATEGORIA;

    private String NOMCATEGORIA;

    private String ESTCATEGORIA;
}
