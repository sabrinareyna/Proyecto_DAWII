package pe.edu.cibertec.ms.pedido.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.ms.pedido.Dto.request.LoginRequest;
import pe.edu.cibertec.ms.pedido.Dto.response.TokenResponse;
import pe.edu.cibertec.ms.pedido.client.AuthClient;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenManager {

    private final AuthClient authClient;

    @Value("${usuario.master.correo}")
    private String correo;

    @Value("${usuario.master.password}")
    private String password;

    // Token actual
    private String token;

    // Fecha de expiración del token
    private long tokenExpiraEn = 0;

    public synchronized String getToken() {
        if (token == null || System.currentTimeMillis() >= tokenExpiraEn) {
            authenticate();
        }
        return token;
    }

    private void authenticate() {
        LoginRequest request = new LoginRequest(correo, password);
        TokenResponse response = authClient.getToken(request);

        this.token = response.getToken();
        long expSegundos = decodeJwtExpiration(token);
        this.tokenExpiraEn = expSegundos * 1000;
    }


    private long decodeJwtExpiration(String jwt) {
        // Divide token en 3 partes: header.payload.signature
        String payload = jwt.split("\\.")[1];
        String json = new String(java.util.Base64.getUrlDecoder().decode(payload));
        try {
            com.fasterxml.jackson.databind.JsonNode node =
                    new com.fasterxml.jackson.databind.ObjectMapper().readTree(json);
            return node.get("exp").asLong();
        } catch (Exception e) {
            throw new RuntimeException("Error decodificando JWT", e);
        }
    }
}

