package pe.edu.cibertec.ms.pedido.Interfaces;


import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.PedidoOneDto;
import pe.edu.cibertec.ms.pedido.Dto.PedidoDto;
import pe.edu.cibertec.ms.pedido.Dto.ProductoPedidoDto;

import java.util.List;

public interface IPedidoRepository {

    List<PedidoDto> getPedidos();
    String insertarPedido(PedidoDetalleRequest pedido);
    PedidoOneDto getOnePedido(int codPedido);
    List<ProductoPedidoDto> getProductosByPedido(int codPedido);
    String actualizarPedido(int codPedido, int codEstado);
    String eliminarPedido(int codPedido);

}
