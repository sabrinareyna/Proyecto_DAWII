package pe.edu.cibertec.ms.pedido.Dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoOneResponse {
    private int codPedido;
    private LocalDate fecPed;
    private String cliente;
    private double precioTotal;
    private String nomEstado;
    private boolean estPed;

    public PedidoOneResponse() {}

    public PedidoOneResponse(int codPedido, LocalDate fecPed, String cliente,
                             double precioTotal, String nomEstado, boolean estPed) {
        this.codPedido = codPedido;
        this.fecPed = fecPed;
        this.cliente = cliente;
        this.precioTotal = precioTotal;
        this.nomEstado = nomEstado;
        this.estPed = estPed;
    }
}
