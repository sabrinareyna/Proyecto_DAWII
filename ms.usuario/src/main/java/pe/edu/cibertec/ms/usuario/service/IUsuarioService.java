package pe.edu.cibertec.ms.usuario.service;

import pe.edu.cibertec.ms.usuario.dto.LoginRequest;
import pe.edu.cibertec.ms.usuario.dto.LoginResponse;
import pe.edu.cibertec.ms.usuario.dto.UsuarioExternalResponse;
import pe.edu.cibertec.ms.usuario.model.RegistrationRequest;
import pe.edu.cibertec.ms.usuario.model.Usuario;

public interface IUsuarioService {

    LoginResponse login(LoginRequest request);

    UsuarioExternalResponse obtenerUsuarioPorId(Integer codUsuario);
    String registrarUsuario(RegistrationRequest usuario);
}
