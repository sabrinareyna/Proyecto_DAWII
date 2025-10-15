package pe.edu.cibertec.ms.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ms.usuario.model.Usuario;
import pe.edu.cibertec.ms.usuario.service.UsuarioService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/registrarUsuario")
    public String registrarUsuario(@RequestBody Usuario usuario){
        return usuarioService.registrarUsuario(usuario);

    }
}

