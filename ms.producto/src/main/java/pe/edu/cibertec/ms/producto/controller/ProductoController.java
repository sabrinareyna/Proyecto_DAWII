package pe.edu.cibertec.ms.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

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
    public String registrarProducto(@ModelAttribute ProductoRequestDTO productoRequest) {
        String rutaImagen = guardarImagen(productoRequest.getImgProducto(), productoRequest.getNomProducto());
        Producto nuevoProducto = mapRequestToProducto(productoRequest, rutaImagen);
        return productoService.mergeProducto(nuevoProducto, "registro");
    }

    @PutMapping("/ActualizarProducto")
    public String actualizarProducto(@ModelAttribute ProductoRequestDTO productoRequest) {
        String rutaImagenGuardada;

        // 1. Verificar si el usuario SUBIÓ una nueva imagen.
        if (productoRequest.getImgProducto() != null && !productoRequest.getImgProducto().isEmpty()) {
            // Opción A: Se subió una nueva imagen, se guarda.
            rutaImagenGuardada = guardarImagen(productoRequest.getImgProducto(), productoRequest.getNomProducto());
        } else {
            // Opción B: NO se subió una nueva imagen.

            // 2. Buscar el producto existente en la base de datos para OBTENER la ruta de la imagen actual.
            Producto productoExistente = productoService.getProductoEntity(productoRequest.getCodProducto());

            // 3. Conservamos la ruta de la imagen que ya estaba en la base de datos.
            rutaImagenGuardada = productoExistente.getImgProducto();
        }

        Producto productoActualizado = mapRequestToProducto(productoRequest, rutaImagenGuardada);
        return productoService.mergeProducto(productoActualizado, "actualización");
    }

    @PutMapping("/CambiarEstadoProducto/{codProducto}")
    public String cambiarEstadoProducto(@PathVariable Integer codProducto) {
        return productoService.cambiarEstadoProducto(codProducto);
    }

    //--------------------------------------
    // --- LÓGICA AUXILIAR ---
    private String guardarImagen(MultipartFile archivo, String nomProducto) {
        if (archivo == null || archivo.isEmpty()) {
            return null;
        }

        String nombreArchivo = nomProducto.replaceAll("[^a-zA-Z0-9.-]", "_") + "_" + System.currentTimeMillis() + ".png";
        Path rutaAbsoluta = Paths.get("src/main/resources/static/imagenes/productos", nombreArchivo);

        try {
            Files.createDirectories(rutaAbsoluta.getParent());
            Files.write(rutaAbsoluta, archivo.getBytes());
            return "/imagenes/productos/" + nombreArchivo;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
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
