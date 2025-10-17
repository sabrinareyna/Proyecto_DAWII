package pe.edu.cibertec.ms.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ms.producto.dto.ProductoDTO;
import pe.edu.cibertec.ms.producto.model.Producto;
import pe.edu.cibertec.ms.producto.service.ProductoService;

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
    public String registrarProducto(@RequestBody Producto producto) {
        return productoService.mergeProducto(producto, "registro");
    }

    @PutMapping("/ActualizarProducto")
    public String actualizarProducto(@RequestBody Producto producto) {
        return productoService.mergeProducto(producto, "actualización");
    }

    @PutMapping("/CambiarEstadoProducto/{codProducto}")
    public String cambiarEstadoProducto(@PathVariable Integer codProducto, @RequestParam Boolean estActual) {
        return productoService.cambiarEstadoProducto(codProducto, estActual);
    }
}
