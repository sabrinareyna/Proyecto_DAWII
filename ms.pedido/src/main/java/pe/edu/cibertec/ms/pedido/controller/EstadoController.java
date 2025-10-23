package pe.edu.cibertec.ms.pedido.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.ms.pedido.Dto.EstadosDto;
import pe.edu.cibertec.ms.pedido.Dto.response.PedidoResponse;
import pe.edu.cibertec.ms.pedido.Interfaces.IEstadoService;
import pe.edu.cibertec.ms.pedido.Interfaces.IPedidoService;
import pe.edu.cibertec.ms.pedido.service.EstadoService;

import java.util.List;

@RestController
@RequestMapping("/estados/")
public class EstadoController {

    @Autowired
    private final IEstadoService estadoService;

    public EstadoController(IEstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping("/SelectEstados")
    public ResponseEntity<?> SelectEstados() {
        List<EstadosDto> estados = estadoService.findAll();
        if (estados == null || estados.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: No se encontraron estados.");
        }
        return ResponseEntity.ok(estados);
    }


}
