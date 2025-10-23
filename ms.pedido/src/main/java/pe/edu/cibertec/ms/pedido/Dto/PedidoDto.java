package pe.edu.cibertec.ms.pedido.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {
    private int codPedido;
    private Date fecPed;
    private String codUsuario;
    private double precioTotal;
    private String nomEstado;
    private boolean estPed;
    private int cantidadTotal;

}
