package pe.edu.cibertec.ms.pedido.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoPedidoDto {
    private int codProducto;
    private double preUni;
    private int cantidad;
}
