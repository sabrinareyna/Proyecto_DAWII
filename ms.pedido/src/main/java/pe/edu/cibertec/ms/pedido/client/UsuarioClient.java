package pe.edu.cibertec.ms.pedido.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.cibertec.ms.pedido.Dto.response.UsuarioResponse;

@FeignClient(name = "usuario-service", url = "http://localhost:8083/usuarios")
public interface UsuarioClient {

    @GetMapping("/{codUsuario}")
    UsuarioResponse obtenerUsuarioPorId(@PathVariable("codUsuario") Integer codUsuario);
}
