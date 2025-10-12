package pe.edu.cibertec.ms.pedido.service;

import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoOneResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;

import java.util.List;

public interface IPedidoService {
    List<PedidoResponse> listarPedidos();

    String registrarPedidoDetalle(PedidoDetalleRequest pedidoDetalle);

    String actualizarPedido(int codPedido, int codEstado);

    String eliminarPedido(int codPedido);

    PedidoOneResponse obtenerPedido(int codPedido);
}
