package pe.edu.cibertec.ms.pedido.Dto.request;

import lombok.Data;

@Data
public class DetallePedidoRequest {
    private int codProducto;
    private int cantidad;
    private double precioUnitario;
    public DetallePedidoRequest() {}

    public DetallePedidoRequest(int codProducto,double precioUnitario ,int cantidad) {
        this.codProducto = codProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

}
