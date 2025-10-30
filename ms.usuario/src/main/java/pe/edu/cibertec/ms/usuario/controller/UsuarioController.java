package pe.edu.cibertec.ms.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.ms.usuario.dto.LoginRequest;
import pe.edu.cibertec.ms.usuario.dto.LoginResponse;
import pe.edu.cibertec.ms.usuario.model.RegistrationRequest;
import pe.edu.cibertec.ms.usuario.service.IUsuarioService;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> registrarse(@RequestBody RegistrationRequest request) {
        try {
            usuarioService.registrarUsuario(request);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Usuario registrado exitosamente.");
            response.put("status", "success");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Error al registrar usuario: " + ex.getMessage());
            error.put("status", "error");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }


}

