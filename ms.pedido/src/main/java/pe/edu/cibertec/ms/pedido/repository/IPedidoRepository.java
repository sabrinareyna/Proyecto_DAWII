package pe.edu.cibertec.ms.pedido.repository;


import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoOneResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.ProductoPedidoResponse;

import java.util.List;

public interface IPedidoRepository {

    List<PedidoResponse> getPedidos();
    String insertarPedido(PedidoDetalleRequest pedido);
    PedidoOneResponse getOnePedido(int codPedido);
    List<ProductoPedidoResponse> getProductosByPedido(int codPedido);
    String actualizarPedido(int codPedido, int codEstado);
    String eliminarPedido(int codPedido);

}
