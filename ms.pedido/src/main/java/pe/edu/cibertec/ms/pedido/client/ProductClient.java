package pe.edu.cibertec.ms.pedido.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.ms.pedido.Dto.response.ProductoResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.UsuarioResponse;

import java.util.Map;

@FeignClient(name = "producto-service", url = "http://localhost:8081/productos")
public interface ProductClient {
    @GetMapping("/ObtenerProducto/{codProducto}")
    ProductoResponse getProductById(@PathVariable("codProducto") Integer codProducto);

    @PutMapping("/ActualizarStock/{codProducto}")
    Map<String, Object> updateStock(
            @PathVariable("codProducto") Integer codProducto,
            @RequestParam("cantidad") Integer cantidad
    );
}
