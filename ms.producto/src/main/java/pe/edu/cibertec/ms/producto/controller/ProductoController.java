package pe.edu.cibertec.ms.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.ms.producto.dto.ProductoDTO;
import pe.edu.cibertec.ms.producto.dto.ProductoRequestDTO;
import pe.edu.cibertec.ms.producto.model.Categoria;
import pe.edu.cibertec.ms.producto.model.Marca;
import pe.edu.cibertec.ms.producto.model.Producto;
import pe.edu.cibertec.ms.producto.service.ProductoService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/ListarPorCategoria/{codCategoria}")
    public List<ProductoDTO> listarPorCategoria(@PathVariable Integer codCategoria) {
        return productoService.getProductosPorCategoria(codCategoria);
    }

    @GetMapping("/ListarTop5ProductosMasBaratos")
    public List<ProductoDTO> listarTop5ProductosMasBaratos() {
        return productoService.getTop5ProductosMasBaratos();
    }

    @GetMapping("/ObtenerProducto/{codProducto}")
    public ProductoDTO obtenerProducto(@PathVariable Integer codProducto) {
        return productoService.getProducto(codProducto);
    }

    @PostMapping("/RegistrarProducto")
    public ResponseEntity<?> registrarProducto(@ModelAttribute ProductoRequestDTO productoRequest) {
        String rutaImagen;

        if (productoRequest.getImgProducto() != null && !productoRequest.getImgProducto().isEmpty()) {
            rutaImagen = guardarImagen(
                    productoRequest.getImgProducto(),
                    productoRequest.getNomProducto(),
                    null
            );
        } else {
            rutaImagen = "default.jpg";
        }

        Producto nuevoProducto = mapRequestToProducto(productoRequest, rutaImagen);
        String mensaje = productoService.mergeProducto(nuevoProducto, "registro");
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", mensaje);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/ActualizarProducto")
    public String actualizarProducto(@ModelAttribute ProductoRequestDTO productoRequest) {
        String rutaImagenFinal;
        if (productoRequest.getImgProducto() != null && !productoRequest.getImgProducto().isEmpty()) {
            rutaImagenFinal = guardarImagen(
                    productoRequest.getImgProducto(),
                    productoRequest.getNomProducto(),
                    productoRequest.getImgActual()
            );
        } else {
            rutaImagenFinal = productoRequest.getImgActual();
        }

        Producto productoActualizado = mapRequestToProducto(productoRequest, rutaImagenFinal);
        return productoService.mergeProducto(productoActualizado, "actualización");
    }

    @PutMapping("/CambiarEstadoProducto/{codProducto}")
    public String cambiarEstadoProducto(@PathVariable Integer codProducto) {
        return productoService.cambiarEstadoProducto(codProducto);
    }


    private static final String RUTA_CARPETA_IMAGENES = "C:/agregados/shopmi/imagenes/productos/";

    private String guardarImagen(MultipartFile imagenNueva, String nombreProducto, String imgActual) {

        String nombreOriginal = imagenNueva.getOriginalFilename();
        String extension = "";

        String nombreBaseSanitizado = nombreProducto.replaceAll("[^a-zA-Z0-9-]", "_").replaceAll("\\s+", "_");

        if (nombreOriginal != null && nombreOriginal.lastIndexOf(".") > 0) {
            extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
        }
        String nombreFinal = nombreBaseSanitizado + "_" + System.currentTimeMillis() + extension;
        Path uploadPath = Paths.get(RUTA_CARPETA_IMAGENES).toAbsolutePath();

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            if (imgActual != null && !imgActual.isEmpty() && !imgActual.equals("default.jpg")) {
                Path rutaAntigua = uploadPath.resolve(imgActual);
                Files.deleteIfExists(rutaAntigua);
                System.out.println("Imagen antigua eliminada: " + imgActual);
            }

            Path rutaCompleta = uploadPath.resolve(nombreFinal);
            Files.write(rutaCompleta, imagenNueva.getBytes());
            System.out.println("Imagen guardada EXITOSAMENTE en: " + rutaCompleta.toString());

            return nombreFinal;

        } catch (IOException e) {
            System.err.println("Error al guardar/eliminar la imagen: " + e.getMessage());
            return imgActual != null ? imgActual : "default.jpg";
        }
    }
    private Producto mapRequestToProducto(ProductoRequestDTO request, String rutaImagen) {
        Producto p = new Producto();
        p.setCodProducto(request.getCodProducto());
        p.setNomProducto(request.getNomProducto());
        p.setDescripcion(request.getDescripcion());
        p.setPreUni(request.getPreUni());
        p.setStock(request.getStock());
        p.setEstProducto(request.getEstProducto());
        p.setImgProducto(rutaImagen);
        p.setCategoria(new Categoria(request.getCodCategoria().longValue()));
        p.setMarca(new Marca(request.getCodMarca().longValue()));

        return p;
    }
}
