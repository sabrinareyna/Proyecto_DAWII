package pe.edu.cibertec.ms.pedido.Dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PedidoDetalleRequest {
    private int codUsuario;
    private LocalDate fecPed;
    private double precioTotal;
    private List<DetallePedidoRequest> detallePedido;

    public PedidoDetalleRequest() {}

    public PedidoDetalleRequest(int codUsuario, LocalDate fecPed, double precioTotal, List<DetallePedidoRequest> detallePedido) {
        this.codUsuario = codUsuario;
        this.fecPed = fecPed;
        this.precioTotal = precioTotal;
        this.detallePedido = detallePedido;
    }

}
