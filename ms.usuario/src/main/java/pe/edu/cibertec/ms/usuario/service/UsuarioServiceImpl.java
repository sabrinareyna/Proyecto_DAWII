package pe.edu.cibertec.ms.usuario.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.usuario.config.JwtTokenUtil;
import pe.edu.cibertec.ms.usuario.dto.LoginRequest;
import pe.edu.cibertec.ms.usuario.dto.LoginResponse;
import pe.edu.cibertec.ms.usuario.dto.UsuarioExternalResponse;
import pe.edu.cibertec.ms.usuario.model.Usuario;
import pe.edu.cibertec.ms.usuario.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(request.getCorreo());

        if (usuarioOpt.isEmpty()) {
            return LoginResponse.builder().mensaje("Error: Correo o contraseña incorrecta.").build();
        }

        Usuario usuario = usuarioOpt.get();

        boolean passwordMatch = passwordEncoder.matches(request.getContrasenia(), usuario.getContrasenia());

        if (!passwordMatch) {
            return LoginResponse.builder().mensaje("Error: Correo o contraseña incorrecta.").build();
        }

        String rol = usuario.getRol().getNombreRol();
        String correo = usuario.getCorreo();

        String jwt = jwtTokenUtil.generateToken(correo, rol);

        return LoginResponse.builder()
                .id(usuario.getCodUsuario())
                .token(jwt)
                .correo(correo)
                .rol(rol)
                .mensaje("Inicio de sesión exitoso.")
                .build();
    }

    @Override
    public UsuarioExternalResponse obtenerUsuarioPorId(Integer codUsuario) {
        List<Object[]> results = usuarioRepository.getUsuarioInfoById(codUsuario);

        if (results.isEmpty() || results.get(0) == null) {
            return null;
        }

        Object[] row = results.get(0);

        Integer cod = ((Number) row[0]).intValue();
        String apeUsuario = (String) row[1];
        String nomUsuario = (String) row[2];

        UsuarioExternalResponse response = new UsuarioExternalResponse();
        response.setCodUsuario(cod);
        response.setApeUsuario(apeUsuario);
        response.setNomUsuario(nomUsuario);
        response.setNombreCompleto(nomUsuario + " " + apeUsuario);

        return response;
    }
}
