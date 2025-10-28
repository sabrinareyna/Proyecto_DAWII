package pe.edu.cibertec.ms.pedido.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.pedido.Dto.PedidoOneDto;
import pe.edu.cibertec.ms.pedido.Dto.ProductoPedidoDto;
import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.*;
import pe.edu.cibertec.ms.pedido.Dto.PedidoDto;
import pe.edu.cibertec.ms.pedido.Interfaces.IPedidoService;
import pe.edu.cibertec.ms.pedido.client.ProductClient;
import pe.edu.cibertec.ms.pedido.client.UsuarioClient;
import pe.edu.cibertec.ms.pedido.Interfaces.IPedidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService {
    private final IPedidoRepository pedidoRepository;
    private final UsuarioClient  usuarioClient;
    private final ProductClient  productClient;
    private final JwtTokenManager jwtTokenManager;


    @Override
    public List<PedidoResponse> listarPedidos() {
        List<PedidoDto> pedidosDb = pedidoRepository.getPedidos();
        List<PedidoResponse> pedidos = new ArrayList<>();
        String token = jwtTokenManager.getToken();
        for (PedidoDto pedidoDb : pedidosDb) {
            PedidoResponse pedido = new PedidoResponse();
            pedido.setCodPedido(pedidoDb.getCodPedido());
            pedido.setFecPed(pedidoDb.getFecPed());
            pedido.setPrecioTotal(pedidoDb.getPrecioTotal());
            pedido.setNomEstado(pedidoDb.getNomEstado());
            pedido.setEstPed(pedidoDb.isEstPed());
            pedido.setCantidadTotal(pedidoDb.getCantidadTotal());

            try {
                UsuarioResponse usuario = usuarioClient.obtenerUsuarioPorId(Integer.parseInt(pedidoDb.getCodUsuario()));
                pedido.setCliente(usuario.getNombreCompleto());
            } catch (Exception e) {
                pedido.setCliente("Usuario no encontrado");
            }

            pedidos.add(pedido);
        }

        return pedidos;
    }

    @Override
    public String registrarPedidoDetalle(PedidoDetalleRequest pedido) {
        return pedidoRepository.insertarPedido(pedido);
    }

    @Override
    public PedidoDetalleResponse obtenerPedido(int codPedido) {
        try {
            PedidoOneDto pedidoDto = pedidoRepository.getOnePedido(codPedido);

            PedidoDetalleResponse response = new PedidoDetalleResponse();

            if (pedidoDto == null) {
                response.setPedido(null);
                response.setProductos(List.of());
                return response;
            }

            PedidoOneResponse pedidoResponse = new PedidoOneResponse();
            pedidoResponse.setCodPedido(pedidoDto.getCodPedido());
            pedidoResponse.setFecPed(pedidoDto.getFecPed());
            pedidoResponse.setPrecioTotal(pedidoDto.getPrecioTotal());
            pedidoResponse.setCodEstado(pedidoDto.getCodEstado());
            pedidoResponse.setEstPed(pedidoDto.isEstPed());

            try {
                String token = jwtTokenManager.getToken();
                UsuarioResponse usuario = usuarioClient.obtenerUsuarioPorId(Integer.parseInt(pedidoDto.getCodUsuario()));
                pedidoResponse.setCliente(usuario.getNombreCompleto());
            } catch (Exception e) {
                pedidoResponse.setCliente("Usuario no encontrado");
            }

            List<ProductoPedidoDto> productosDto = pedidoRepository.getProductosByPedido(codPedido);

            List<ItemProductoResponse> productos = new ArrayList<>();
            for (ProductoPedidoDto prod : productosDto) {
                try {
                    ProductoResponse prodResp = productClient.getProductById(prod.getCodProducto());
                    ItemProductoResponse item = new ItemProductoResponse();
                    item.setNomProducto(prodResp.getNomProducto());
                    item.setImgProducto(prodResp.getImgProducto());
                    item.setPreUni(prod.getPreUni());
                    item.setCantidad(prod.getCantidad());
                    productos.add(item);
                } catch (Exception e) {
                    ItemProductoResponse item = new ItemProductoResponse();
                    item.setNomProducto("Producto no encontrado");
                    item.setImgProducto("default.jpg");
                    item.setPreUni(prod.getPreUni());
                    item.setCantidad(prod.getCantidad());
                    productos.add(item);
                }
            }

            response.setPedido(pedidoResponse);
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
