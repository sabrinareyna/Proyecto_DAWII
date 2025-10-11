package pe.edu.cibertec.ms.producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.ms.producto.model.Producto;
import pe.edu.cibertec.ms.producto.service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/categoria/{codCategoria}")
    public List<Producto> listarPorCategoria(@PathVariable int codCategoria) {
        return productoService.listarPorCategoria(codCategoria);
    }

    @GetMapping("/top5")
    public List<Producto> listarTop5MasBaratos() {
        return productoService.listarTop5MasBaratos();
    }

    @GetMapping("/{codProducto}")
    public Producto obtenerPorId(@PathVariable int codProducto) {
        return productoService.obtenerPorId(codProducto);
    }

    @PostMapping
    public String registrarProducto(@RequestBody Producto producto) {
        return productoService.registrarProducto(producto);
    }

    @PutMapping
    public String actualizarProducto(@RequestBody Producto producto) {
        return productoService.actualizarProducto(producto);
    }

    @PutMapping("/{codProducto}/estado")
    public String cambiarEstado(@PathVariable int codProducto, @RequestParam boolean estadoActual) {
        return productoService.cambiarEstadoProducto(codProducto, estadoActual);
    }
}
