package pe.edu.cibertec.ms.pedido.service;

import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoOneResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;
import pe.edu.cibertec.ms.pedido.repository.IPedidoRepository;

import java.util.List;

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
    public String registrarPedidoDetalle(PedidoDetalleRequest pedidoDetalle) {
        return "";
    }

    @Override
    public String actualizarPedido(int codPedido, int codEstado) {
        return "";
    }

    @Override
    public String eliminarPedido(int codPedido) {
        return "";
    }

    @Override
    public PedidoOneResponse obtenerPedido(int codPedido) {
        return null;
    }
}
