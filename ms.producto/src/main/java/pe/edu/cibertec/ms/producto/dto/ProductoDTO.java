package pe.edu.cibertec.ms.producto.dto;


import java.math.BigDecimal;

public interface ProductoDTO {
    Integer getCodProducto();
    String getNomCategoria();
    String getNomMarca();
    String getImgProducto();
    String getNomProducto();
    String getDescripcion();
    BigDecimal getPreUni();
    Integer getStock();
    Boolean getEstProducto();
}
