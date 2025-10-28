package pe.edu.cibertec.ms.pedido.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoOneDto {
    private int codPedido;
    private String codUsuario;
    private LocalDate fecPed;
    private double precioTotal;
    private String codEstado;
    private boolean estPed;
}
