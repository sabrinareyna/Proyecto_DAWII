package pe.edu.cibertec.ms.producto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name ="PRODUCTO")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODPRODUCTO", nullable = false)
    private Long idpro;

    // 🔹 Relación con CATEGORIA
    @ManyToOne
    @JoinColumn(name = "CODCATEGORIA", nullable = false)
    private Categoria idcat;

    @Column(name = "IMGPRODUCTO", nullable = false, length = 50)
    private String imgprod;

    @Column(name = "NOMPRODUCTO", nullable = false, length = 70)
    private String nomprod;

    @Column(name = "DESCRIPCION", nullable = false, length = 300)
    private String desprod;

    @Column(name = "PREUNI", nullable = false, precision = 10, scale = 2)
    private BigDecimal preuni;

    // Relación con MARCA
    @ManyToOne
    @JoinColumn(name = "CODMARCA", nullable = false)
    private Marca id;

    @Column(name = "STOCK", nullable = false)
    private int stock;

    @Column(name = "ESTPRODUCTO", nullable = false)
    private boolean estProducto;
}
