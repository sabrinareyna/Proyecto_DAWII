package pe.edu.cibertec.ms.usuario.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String correo;
    private String rol;
    private String mensaje;
}
