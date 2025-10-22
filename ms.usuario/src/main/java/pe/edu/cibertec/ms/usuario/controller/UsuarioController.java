package pe.edu.cibertec.ms.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.ms.usuario.dto.LoginRequest;
import pe.edu.cibertec.ms.usuario.dto.LoginResponse;
import pe.edu.cibertec.ms.usuario.service.IUsuarioService;

@RestController
@RequestMapping("/inicio")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/iniciarsesion") // Solo se necesita la sub-ruta
    public ResponseEntity<LoginResponse> iniciarSesion(@RequestBody LoginRequest loginRequest) {

        LoginResponse response = usuarioService.login(loginRequest);

        if (response.getToken() == null || response.getToken().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/registrarse")
    public ResponseEntity<?> registrarse(/* @RequestBody RegistrationRequest request */) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro pendiente de implementación.");
    }
}

