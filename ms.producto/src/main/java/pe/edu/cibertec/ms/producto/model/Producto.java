package pe.edu.cibertec.ms.producto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "PRODUCTO")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODPRODUCTO", nullable = false)
    private Integer codProducto;

    @ManyToOne
    @JoinColumn(name = "CODCATEGORIA", referencedColumnName = "CODCATEGORIA", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "CODMARCA", referencedColumnName = "CODMARCA", nullable = false)
    private Marca marca;

    @Column(name = "IMGPRODUCTO", nullable = false, length = 500)
    private String imgProducto;

    @Column(name = "NOMPRODUCTO", nullable = false, length = 70)
    private String nomProducto;

    @Column(name = "DESCRIPCION", nullable = false, length = 300)
    private String descripcion;

    @Column(name = "PREUNI", nullable = false, precision = 10, scale = 2)
    private BigDecimal preUni;

    @Column(name = "STOCK", nullable = false)
    private Integer stock;

    @Column(name = "ESTPRODUCTO", nullable = false)
    private Boolean estProducto;
}

