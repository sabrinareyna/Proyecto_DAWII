package pe.edu.cibertec.ms.pedido.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;
import pe.edu.cibertec.ms.pedido.service.IPedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final IPedidoService pedidoService;

    public PedidoController(IPedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/ListarPedidos")
    public ResponseEntity<?> listarPedidos() {
        List<PedidoResponse> pedidos = pedidoService.listarPedidos();

        if (pedidos == null || pedidos.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: No se encontraron pedidos.");
        }

        return ResponseEntity.ok(pedidos);
    }
}
