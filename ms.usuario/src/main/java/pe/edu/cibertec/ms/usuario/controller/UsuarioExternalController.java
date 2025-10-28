package pe.edu.cibertec.ms.usuario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.ms.usuario.dto.UsuarioExternalResponse;
import pe.edu.cibertec.ms.usuario.service.IUsuarioService;

@RestController
public class UsuarioExternalController {

    private final IUsuarioService usuarioService;

    public UsuarioExternalController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios/{codUsuario}")
    public ResponseEntity<UsuarioExternalResponse> obtenerUsuarioPorId(@PathVariable Integer codUsuario) {

        UsuarioExternalResponse usuario = usuarioService.obtenerUsuarioPorId(codUsuario);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }
}
