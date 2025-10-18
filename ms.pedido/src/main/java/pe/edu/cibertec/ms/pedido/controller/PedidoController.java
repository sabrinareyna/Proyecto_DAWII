package pe.edu.cibertec.ms.pedido.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ms.pedido.Dto.request.PedidoDetalleRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoDetalleResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;
import pe.edu.cibertec.ms.pedido.service.IPedidoService;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/ObtenerPedido/{codPedido}")
    public ResponseEntity<PedidoDetalleResponse> obtenerPedido(@PathVariable int codPedido) {
        PedidoDetalleResponse result = pedidoService.obtenerPedido(codPedido);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/RegistrarPedido")
    public ResponseEntity<String> registrarPedido(@RequestBody PedidoDetalleRequest pedido) {
        if (pedido.getFecPed().isBefore(java.time.LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha del pedido no es válida.");
        }
        return ResponseEntity.ok(pedidoService.registrarPedidoDetalle(pedido));
    }

    @PutMapping("/ActualizarPedido/{codPedido}")
    public ResponseEntity<String> actualizarPedido(@PathVariable int codPedido, @RequestBody int codEstado) {
        try {
            String response = pedidoService.actualizarPedido(codPedido, codEstado);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar pedido: " + e.getMessage());
        }
    }

    @DeleteMapping("/EliminarPedido/{codPedido}")
    public ResponseEntity<Map<String, String>> eliminarPedido(@PathVariable int codPedido) {
        Map<String, String> mensaje = pedidoService.eliminarPedido(codPedido);
        return ResponseEntity.ok(mensaje);
    }






}
