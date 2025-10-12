package pe.edu.cibertec.ms.pedido.Dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class PedidoResponse {
    private int codPedido;
    private Date fecPed;
    private String cliente;
    private double precioTotal;
    private String nomEstado;
    private int cantidadTotal;
    private boolean estPed;
    public PedidoResponse() {}

    public PedidoResponse(int codPedido, Date fecPed, String cliente, double precioTotal,
                          String nomEstado, int cantidadTotal, boolean estPed) {
        this.codPedido = codPedido;
        this.fecPed = fecPed;
        this.cliente = cliente;
        this.precioTotal = precioTotal;
        this.nomEstado = nomEstado;
        this.cantidadTotal = cantidadTotal;
        this.estPed = estPed;
    }
}
