package pe.edu.cibertec.ms.producto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.ms.producto.model.Producto;
import pe.edu.cibertec.ms.producto.repository.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarPorCategoria(int codCategoria) {
        return productoRepository.listarPorCategoria(codCategoria);
    }

    public List<Producto> listarTop5MasBaratos() {
        return productoRepository.listarTop5MasBaratos();
    }

    public Producto obtenerPorId(int codProducto) {
        return productoRepository.obtenerPorId(codProducto);
    }

    public String registrarProducto(Producto producto) {
        productoRepository.registrarOActualizarProducto(
                0,
                producto.getCategoria().getCodCategoria().intValue(),
                producto.getImgProducto(),
                producto.getNomProducto(),
                producto.getDescripcion(),
                producto.getPreUni().doubleValue(),
                producto.getMarca().getCodMarca().intValue(),
                producto.getStock(),
                producto.isEstProducto()
        );
        return "Producto registrado correctamente.";
    }

    public String actualizarProducto(Producto producto) {
        productoRepository.registrarOActualizarProducto(
                producto.getCodProducto().intValue(),
                producto.getCategoria().getCodCategoria().intValue(),
                producto.getImgProducto(),
                producto.getNomProducto(),
                producto.getDescripcion(),
                producto.getPreUni().doubleValue(),
                producto.getMarca().getCodMarca().intValue(),
                producto.getStock(),
                producto.isEstProducto()
        );
        return "Producto actualizado correctamente.";
    }

    public String cambiarEstadoProducto(int codProducto, boolean estadoActual) {
        if (estadoActual) {
            productoRepository.eliminarProducto(codProducto);
            return "Producto deshabilitado correctamente.";
        } else {
            productoRepository.habilitarProducto(codProducto);
            return "Producto habilitado correctamente.";
        }
    }
}

