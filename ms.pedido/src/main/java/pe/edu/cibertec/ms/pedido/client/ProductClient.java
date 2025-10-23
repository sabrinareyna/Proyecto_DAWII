package pe.edu.cibertec.ms.pedido.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.cibertec.ms.pedido.Dto.response.ProductoResponse;
import pe.edu.cibertec.ms.pedido.Dto.response.UsuarioResponse;

@FeignClient(name = "producto-service", url = "http://localhost:8081/productos")
public interface ProductClient {
    @GetMapping("/ObtenerProducto/{codProducto}")
    ProductoResponse getProductById(@PathVariable("codProducto") Integer codProducto);
}
