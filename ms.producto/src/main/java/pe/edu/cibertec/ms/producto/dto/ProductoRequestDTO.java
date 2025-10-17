package pe.edu.cibertec.ms.producto.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoRequestDTO {
    private Integer codCategoria;
    private Integer codMarca;
    private String imgProducto;
    private String nomProducto;
    private String descripcion;
    private BigDecimal preUni;
    private Integer stock;
    private Boolean estProducto;
}
