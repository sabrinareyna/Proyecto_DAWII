package pe.edu.cibertec.ms.usuario.dto;


import lombok.Data;

@Data
public class UsuarioExternalResponse {
    private Integer codUsuario;
    private String apeUsuario;
    private String nomUsuario;

    private String nombreCompleto;
}
