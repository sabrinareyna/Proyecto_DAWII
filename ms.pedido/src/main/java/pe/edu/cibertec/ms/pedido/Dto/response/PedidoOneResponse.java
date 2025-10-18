package pe.edu.cibertec.ms.pedido.Dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoOneResponse {
    private int codPedido;
    private String cliente;
    private LocalDate fecPed;
    private double precioTotal;
    private String nomEstado;
    private boolean estPed;

    public PedidoOneResponse() {}

    public PedidoOneResponse(int codPedido,String cliente, LocalDate fecPed,
                             double precioTotal, String nomEstado, boolean estPed) {
        this.codPedido = codPedido;
        this.cliente = cliente;
        this.fecPed = fecPed;
        this.precioTotal = precioTotal;
        this.nomEstado = nomEstado;
        this.estPed = estPed;
    }
}
