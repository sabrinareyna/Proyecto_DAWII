package pe.edu.cibertec.ms.pedido.Dto.request;

import lombok.Data;

@Data
public class DetallePedidoRequest {
    private int codProducto;
    private int cantidad;
    private double preUni;
    public DetallePedidoRequest() {}

    public DetallePedidoRequest(int codProducto,double preUni ,int cantidad) {
        this.codProducto = codProducto;
        this.preUni = preUni;
        this.cantidad = cantidad;
    }

}
