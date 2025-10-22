package pe.edu.cibertec.ms.usuario.service;

import pe.edu.cibertec.ms.usuario.dto.LoginRequest;
import pe.edu.cibertec.ms.usuario.dto.LoginResponse;
import pe.edu.cibertec.ms.usuario.dto.UsuarioExternalResponse;

public interface IUsuarioService {

    LoginResponse login(LoginRequest request);

    UsuarioExternalResponse obtenerUsuarioPorId(Integer codUsuario);
}
