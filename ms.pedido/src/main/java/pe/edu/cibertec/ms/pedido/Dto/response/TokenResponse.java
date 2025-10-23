package pe.edu.cibertec.ms.pedido.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private String expiration;
}
