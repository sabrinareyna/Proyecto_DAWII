package pe.edu.cibertec.ms.pedido.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemProductoResponse {
    private String imgProducto;
    private String nomProducto;
    private double preUni;
    private int cantidad;
}
