package pe.edu.cibertec.ms.usuario.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private final Key key;
    private final long expiration;
    
    public JwtTokenUtil(@Value("${app.jwt.secret}") String secret,
                        @Value("${app.jwt.expiration}") long expiration){
       key = Keys.hmacShaKeyFor(secret.getBytes());
       this.expiration = expiration;
   }
    
    public String generateToken(String correo, String rol){
         return Jwts.builder()
                     .setSubject(correo)
                 .claim("rol", rol)
                     .setIssuedAt(new Date())
                     .setExpiration(new Date(System.currentTimeMillis() + expiration))
                     .signWith(key, SignatureAlgorithm.HS256)
                     .compact();
    }
    
    public boolean validateToken(String token){
       try{
           System.out.println("Token a validar: " + token);
           Jwts.parserBuilder().setSigningKey(key)
                   .build().parseClaimsJws(token);
           return true;
       }catch(ExpiredJwtException ex){
           System.out.println("Token expirado: " + token);
           return false;
       }catch(Exception ex){
           System.out.println("Token inválido o error desconocido: " + ex.getMessage());
           return false;
       }
   }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    public String getCorreoFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key)
                    .build().parseClaimsJws(token)
                    .getBody().getSubject(); 
    }

    public String getRolFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key)
                .build().parseClaimsJws(token)
                .getBody().get("rol", String.class);
    }
}
