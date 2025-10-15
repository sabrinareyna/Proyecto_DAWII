package pe.edu.cibertec.ms.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ms.usuario.model.Usuario;
import pe.edu.cibertec.ms.usuario.service.UsuarioService;

import java.util.List;

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

