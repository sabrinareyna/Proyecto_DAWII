package pe.edu.cibertec.ms.pedido.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.cibertec.ms.pedido.Dto.PedidoOneDto;
import pe.edu.cibertec.ms.pedido.Dto.ProductoPedidoDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDetalleResponse {
    private PedidoOneResponse pedido;
    private List<ItemProductoResponse> productos;
}
