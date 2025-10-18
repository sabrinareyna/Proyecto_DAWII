package pe.edu.cibertec.ms.pedido.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoPedidoResponse {
    private String imgProducto;
    private String nomProducto;
    private BigDecimal preUni;
    private int cantidad;
}
