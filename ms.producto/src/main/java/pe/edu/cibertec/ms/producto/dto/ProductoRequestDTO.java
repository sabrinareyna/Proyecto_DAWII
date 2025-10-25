package pe.edu.cibertec.ms.producto.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductoRequestDTO {
    private Integer codProducto;
    private Integer codCategoria;
    private Integer codMarca;

    private MultipartFile imgProducto;

    private String nomProducto;
    private String descripcion;
    private BigDecimal preUni;
    private Integer stock;
    private Boolean estProducto;


}
