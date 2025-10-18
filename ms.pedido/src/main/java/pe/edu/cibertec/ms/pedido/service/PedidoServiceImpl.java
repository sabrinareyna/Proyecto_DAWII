package pe.edu.cibertec.ms.pedido.service;

import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoDetalleResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoOneResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;
import pe.edu.cibertec.ms.pedido.repository.IPedidoRepository;

import java.util.List;
import java.util.Map;

@Service
public class PedidoServiceImpl implements IPedidoService {
    private final IPedidoRepository pedidoRepository;

    public PedidoServiceImpl(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    @Override
    public List<PedidoResponse> listarPedidos() {
        return pedidoRepository.getPedidos();
    }

    @Override
    public String registrarPedidoDetalle(PedidoDetalleRequest pedido) {
        return pedidoRepository.insertarPedido(pedido);
    }

    @Override
    public PedidoDetalleResponse obtenerPedido(int codPedido) {
        try {
            var pedido = pedidoRepository.getOnePedido(codPedido);

            PedidoDetalleResponse response = new PedidoDetalleResponse();

            if (pedido == null) {
                // Pedido no existe → retornamos estructura vacía
                response.setPedido(null);
                response.setProductos(List.of());
                return response;
            }

            var productos = pedidoRepository.getProductosByPedido(codPedido);

            response.setPedido(pedido);
            response.setProductos(productos);

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener pedido: " + e.getMessage(), e);
        }
    }

    @Override
    public String actualizarPedido(int codPedido, int codEstado) {
        return pedidoRepository.actualizarPedido(codPedido, codEstado);
    }

    @Override
    public Map<String, String> eliminarPedido(int codPedido) {
        var pedido = pedidoRepository.getOnePedido(codPedido);

        if (pedido == null) {
            return Map.of("mensaje", "Pedido no encontrado.");
        }
        if (!pedido.isEstPed()) {
            return Map.of("mensaje", "El pedido ya está desactivado.");
        }
        String mensajeRepo = pedidoRepository.eliminarPedido(codPedido);

        if (mensajeRepo == null) {
            return Map.of("mensaje", "No se eliminó el pedido.");
        }

        return Map.of("mensaje", mensajeRepo);
    }



}
