package pe.edu.cibertec.ms.pedido.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.edu.cibertec.ms.pedido.Dto.request.LoginRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.TokenResponse;

@FeignClient(name = "Usuario-service", url = "http://localhost:8083")
public interface AuthClient {
    @PostMapping("/inicio/iniciarsesion")
    TokenResponse getToken(@RequestBody LoginRequest request);
}
