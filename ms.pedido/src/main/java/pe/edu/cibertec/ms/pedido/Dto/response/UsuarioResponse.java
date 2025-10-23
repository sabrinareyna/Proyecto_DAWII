package pe.edu.cibertec.ms.pedido.Dto.response;

import lombok.Data;

@Data
public class UsuarioResponse {
    private Integer codUsuario;
    private String apeUsuario;
    private String nomUsuario;

    private String nombreCompleto;
}
