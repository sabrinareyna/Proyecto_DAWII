package pe.edu.cibertec.ms.producto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "MARCA")
@NoArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODMARCA", nullable = false)
    private Long codMarca;

    @Column(name = "NOMBREMARCA", nullable = false, length = 25) // 🔹 aquí el cambio
    private String nomMarca;

    @Column(name = "ESTMARCA", nullable = false)
    private boolean estMarca;

    public Marca(Long codMarca) {
        this.codMarca = codMarca;
    }
}
