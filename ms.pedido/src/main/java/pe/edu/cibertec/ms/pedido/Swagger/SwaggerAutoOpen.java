package pe.edu.cibertec.ms.pedido.Swagger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class SwaggerAutoOpen implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("🚀 Ejecutando SwaggerAutoOpen...");
        String url = "http://localhost:8082/swagger-ui/index.html";
        try {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
            } else {
                Runtime runtime = Runtime.getRuntime();
                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {
                    runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                } else if (os.contains("mac")) {
                    runtime.exec("open " + url);
                } else if (os.contains("nix") || os.contains("nux")) {
                    runtime.exec("xdg-open " + url);
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ No se pudo abrir Swagger automáticamente: " + e.getMessage());
        }
    }
}
