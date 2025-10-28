package pe.edu.cibertec.ms.producto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // ruta externa
    private static final String RUTA_CARPETA_IMAGENES_NUEVAS = "C:/agregados/shopmi/imagenes/productos/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/imagenes/**")
                .addResourceLocations("file:///" + RUTA_CARPETA_IMAGENES_NUEVAS)
                .addResourceLocations("classpath:/static/imagenes/productos/");
    }
}