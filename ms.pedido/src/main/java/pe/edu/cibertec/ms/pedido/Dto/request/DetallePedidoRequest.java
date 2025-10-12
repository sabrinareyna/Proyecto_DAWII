package pe.edu.cibertec.ms.pedido.Dto.request;

import lombok.Data;

@Data
public class DetallePedidoRequest {
    private int codProducto;
    private int cantidad;
    private double precioUnitario;
    public DetallePedidoRequest() {}

    public DetallePedidoRequest(int codProducto, int cantidad, double precioUnitario) {
        this.codProducto = codProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

}
