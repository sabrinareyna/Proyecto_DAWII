package pe.edu.cibertec.ms.pedido.Interfaces;

import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoDetalleResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;

import java.util.List;
import java.util.Map;

public interface IPedidoService {
    List<PedidoResponse> listarPedidos();
    String registrarPedidoDetalle(PedidoDetalleRequest pedidoDetalle);
    PedidoDetalleResponse obtenerPedido(int codPedido);
    String actualizarPedido(int codPedido, int codEstado);
    Map<String, String> eliminarPedido(int codPedido);


}
