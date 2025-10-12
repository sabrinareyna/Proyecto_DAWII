package pe.edu.cibertec.ms.pedido.repository;


import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;

import java.util.List;

public interface IPedidoRepository {

    List<PedidoResponse> getPedidos();
    String insertarPedido(PedidoDetalleRequest pedido);
    String actualizarPedido(int codPedido, int codEstado);
    String eliminarPedido(int codPedido);


}
