package pe.edu.cibertec.ms.pedido.Dto.response;

import lombok.Data;

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
}
