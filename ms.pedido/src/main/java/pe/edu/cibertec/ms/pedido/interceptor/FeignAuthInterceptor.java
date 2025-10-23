package pe.edu.cibertec.ms.pedido.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.ms.pedido.service.JwtTokenManager;

@Component
@RequiredArgsConstructor
public class FeignAuthInterceptor implements RequestInterceptor {
    private final JwtTokenManager jwtTokenManager;
    @Override
    public void apply(RequestTemplate template) {
        String token = jwtTokenManager.getToken();
        template.header("Authorization", "Bearer "+ token);
    }
}
