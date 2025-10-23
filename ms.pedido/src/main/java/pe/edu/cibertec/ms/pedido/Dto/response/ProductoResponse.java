package pe.edu.cibertec.ms.pedido.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {
    private int codProducto;
    private String nomProducto;
    private String descripcion;
    private boolean estProducto;
    private String imgProducto;
    private double preUni;
    private int stock;
    private String nomMarca;
    private String nomCategoria;
}
