package pe.edu.cibertec.ms.pedido.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor        // genera constructor vacío
@AllArgsConstructor
public class LoginRequest {
    private String correo;
    private String contrasenia;
}

